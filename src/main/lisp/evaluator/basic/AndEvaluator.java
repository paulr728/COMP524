package main.lisp.evaluator.basic;

import java.util.ArrayList;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;
public class AndEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		if (expr instanceof NilAtom || expr.getHead() instanceof NilAtom || expr.getTail() instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'and'");
		}
		SExpression firstEvaled = expr.getHead().eval(environment);
//		if(!(expr.getTail().getTail().eval(environment) instanceof NilAtom)) {
//			throw new IllegalStateException("Too many arguments for operator 'and'");
//		}
		
		if(!(firstEvaled instanceof NilAtom)) {
			SExpression secondEvaled = expr.getTail().getHead().eval(environment);
			if(secondEvaled instanceof NilAtom) {
				return new NilAtom();
			}
			else 
				return secondEvaled;
		}
		else {
			return new NilAtom();
		}
	}

}
