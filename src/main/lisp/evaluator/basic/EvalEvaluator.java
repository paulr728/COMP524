package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class EvalEvaluator implements Evaluator{

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		if(expr instanceof NilAtom ) {
			throw new IllegalStateException("Missing arguments for operator 'eval'");
		}
		if(!(expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'eval'");
		}
		SExpression ret = expr.eval(environment);
		return ret.eval(environment);
	}

}
