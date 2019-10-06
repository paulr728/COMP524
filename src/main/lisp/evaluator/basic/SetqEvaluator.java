package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class SetqEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		
		if (expr instanceof NilAtom || expr.getHead() instanceof NilAtom || expr.getTail() instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'setq'");
		}
		if (!(expr.getTail().getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'setq'");
		}
		
		if(!(expr.getHead() instanceof IdentifierAtom)){
			throw new IllegalStateException("The first argument for operator 'setq' is not a symbol");
		}
		
		SExpression param = expr.getTail();
		IdentifierAtom id = (IdentifierAtom)expr.getHead();
		SExpression ret = param.getHead().eval(environment);
		environment.assign(id, ret);
		return ret;
	}

}
