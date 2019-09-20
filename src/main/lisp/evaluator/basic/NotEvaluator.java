package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;

public class NotEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		
		if(expr instanceof NilAtom ) {
			throw new IllegalStateException("Missing arguments for operator 'not'");
		}
		if(!(expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'not'");
		}
		
		SExpression H = expr.eval(environment);
		
		if(H instanceof NilAtom) {
			return new TAtom();
		}
		else
		{
			return new NilAtom();
		}
	}

}
