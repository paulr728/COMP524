package main.lisp.evaluator.basic;

import java.util.ArrayList;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;

public class CondEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		
		if(expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'cond'");
		}
		
		// special case for (cond T) -> T
		if(expr.getHead() instanceof TAtom && expr.getTail() instanceof NilAtom) {
			return new TAtom();
		}
		
		while(!(expr instanceof NilAtom)) {
			SExpression newHead = expr.getHead();
			if(newHead.getHead().eval(environment) instanceof TAtom) {
				return newHead.getTail().eval(environment);
			}
			expr = expr.getTail();
		}
		
		return new NilAtom();
	}

}
