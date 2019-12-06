package main.lisp.evaluator.basic;

import java.util.List;
import java.util.Optional;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.Function;
import main.lisp.evaluator.function.Lambda;
import main.lisp.evaluator.parallel.args.ArgumentEvaluator;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class FuncallEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		// TODO Auto-generated method stub
		SExpression fun = expr.getTail().getHead().eval(environment);
		if(fun instanceof IdentifierAtom) {
			SExpression ret = new NilAtom();
			expr = expr.getTail();
			List<SExpression> parts = ArgumentEvaluator.split(expr);
			for(int i = parts.size() - 1; i > 0; --i) {
				ret = ExpressionFactory.newInstance(parts.get(i), ret);
			}
			ret = ExpressionFactory.newInstance(fun, ret);
			return ret.eval(environment);
		}
		else if(fun instanceof Lambda || fun instanceof Function) {
			Lambda functionBody = null;
			if(fun instanceof Function) {
				functionBody = ((Function) fun).getLambda();
			}
			else {
				functionBody = ((Lambda)fun);
			}
			// funcall (lambda ...) 
			IdentifierAtom[] formalParams = functionBody.getArgumentNames();
			int numArgs = formalParams.length;
			SExpression realParams = expr.getTail().getTail();
			if(numArgs == 0) {
				if(!(realParams instanceof NilAtom)) {
					throw new IllegalStateException("Too many parameters in funcall.");
				}
				else {
					return functionBody.eval(environment);
				}
			}
			
			if(realParams instanceof NilAtom && numArgs > 0) {
				throw new IllegalStateException("Missing arguments for operator 'funcall' evaluation");
			}
			Environment childEnv = environment.newChild();
			// function object has some variables in its environment
			if(fun instanceof Function) {
				childEnv = ((Function) fun).getEnvironment().newChild();
			}
			
			for(int i = 0; i < formalParams.length; ++i) {
				if(realParams instanceof NilAtom) {
					throw new IllegalStateException("Missing arguments for operator 'funcall' evaluation");
				}
				childEnv.assign(formalParams[i], new NilAtom());
				childEnv.assign(formalParams[i], realParams.getHead().eval(environment));
				realParams = realParams.getTail();
			}
			if(!(realParams instanceof NilAtom)) {
				throw new IllegalStateException("Too many arguments for 'funcall' evaluation");
			}
			return functionBody.eval(childEnv);
		}
		else
		{
			throw new IllegalStateException(fun.toString() + " is not a function name.");
		}
	}

}
