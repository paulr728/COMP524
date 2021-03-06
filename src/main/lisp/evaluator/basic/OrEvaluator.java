package main.lisp.evaluator.basic;

import java.util.ArrayList;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
public class OrEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		if (expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'or'");
		}
		ArrayList<SExpression> exprList = new ArrayList<SExpression>();
		
		while(!(expr instanceof NilAtom) ) {
			exprList.add(expr.getHead());
			expr = expr.getTail();
		}
		
		for(int i = 0; i < exprList.size(); ++i) {
			SExpression ret = exprList.get(i).eval(environment);
			if(ret instanceof NilAtom) {
				continue;
			}
			return ret;
		}
		return new NilAtom();
	}

}
