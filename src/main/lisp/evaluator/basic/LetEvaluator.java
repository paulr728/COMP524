package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class LetEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		if(expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'let'");
		}
		Environment childEnv = environment.newChild();
		SExpression declare = expr.getHead();
		while(!(declare instanceof NilAtom)) {
			SExpression var = declare.getHead();
			childEnv.assign((IdentifierAtom) var.getHead(), var.getTail().eval(environment));
			declare = declare.getTail();
		}
		
		SExpression body = expr.getTail();
		while(!(body.getTail() instanceof NilAtom )) {
			// optional expressions
			body.getHead().eval(childEnv);
			body = body.getTail();
		}
		return body.getHead().eval(childEnv);
	}

}
