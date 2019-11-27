package main.lisp.parser.terms;

import java.util.Optional;

import main.lisp.evaluator.BuiltinOperationManagerSingleton;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.Function;
import main.lisp.evaluator.function.Lambda;

public class NewCompositeExpression extends NewBasicExpression {

	public NewCompositeExpression(SExpression head, SExpression tail) {
		super(head, tail);
		// TODO Auto-generated constructor stub
	}
	
	private SExpression EvaluateLambdaHelper(Lambda functionBody, Environment environment, String errorMsg) {
		IdentifierAtom[] formalParams = functionBody.getArgumentNames();
		int numArgs = formalParams.length;
		SExpression realParams = this.getTail();
		if(realParams instanceof NilAtom && numArgs > 0) {
			throw new IllegalStateException(errorMsg);
		}
		Environment childEnv = environment.newChild();
		
		for(int i = 0; i < formalParams.length; ++i) {
			if(realParams instanceof NilAtom) {
				throw new IllegalStateException(errorMsg);
			}
			childEnv.assign(formalParams[i], realParams.getHead().eval(environment));
			realParams = realParams.getTail();
		}
		if(!(realParams instanceof NilAtom)) {
			throw new IllegalStateException(errorMsg);
		}
		
		return functionBody.eval(childEnv);
	}

	@Override
	public SExpression eval(Environment environment) {
		// TODO Auto-generated method stub
		if(this.getHead() instanceof IdentifierAtom) {
			// regular operations && lambda declaration
			Optional<SExpression> s = environment.lookup((IdentifierAtom) this.getHead());
			Optional<Function> defFun = environment.lookupFun((IdentifierAtom) getHead());
			if(s.isEmpty() && (defFun == null || defFun.isEmpty())) {
				SExpression ret = super.eval(environment);
				return ret;
			}
			else if(!defFun.isEmpty() ) {
				String errorMsg = "Incorrect arguments for operator 'def' evaluation";
				
				return EvaluateLambdaHelper( defFun.get().getLambda(), environment, errorMsg);
			}
			else {
				return s.get();
			}
		}
		else if(this.getHead().isList()) {
			// possibly lambda application
			SExpression headAtom = this.getHead().eval(environment);
			if(!(headAtom instanceof Lambda) && !(headAtom instanceof Function)) {
				return headAtom;
			}
			
			Lambda functionBody = null;
			String errorMsg = "";
			if(headAtom instanceof Lambda) {
				functionBody = (Lambda) headAtom;
				errorMsg = "Incorrect arguments for operator 'lambda' evaluation";
			}
			else if(headAtom instanceof Function) {
				functionBody = ((Function) headAtom).getLambda();
				errorMsg = "Incorrect arguments for operator 'function' evaluation";
			}
			else {
				throw new IllegalStateException("Unknown function body");
			}
				
			return EvaluateLambdaHelper(functionBody, environment, errorMsg);
		}
		else if(this.getHead() instanceof Atom) {
			return this.getHead();
		}
		throw new IllegalStateException("Unsupported header");
	}

}
