package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.FunctionFactory;
import main.lisp.evaluator.function.Lambda;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class FunctionEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment env) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		if(expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'function'");
		}
		Lambda l = (Lambda) expr.getHead().eval(env);
		return FunctionFactory.newInstance(l, env);
	}

}
