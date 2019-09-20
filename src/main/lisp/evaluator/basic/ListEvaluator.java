package main.lisp.evaluator.basic;

import java.util.ArrayList;
import java.util.Collections;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.Atom;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class ListEvaluator implements Evaluator{

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		if(expr instanceof NilAtom)
		{
			return new NilAtom();
		}
		else if(expr instanceof Atom) {
			return ExpressionFactory.newInstance(expr, new NilAtom()); 
		}
		
		ArrayList<SExpression> exprList = new ArrayList<SExpression>();
		
		while(!(expr instanceof NilAtom) ) {
			exprList.add(expr.getHead());
			expr = expr.getTail();
		}
		
		// reconstruct expressions from list	
		Collections.reverse(exprList);
		SExpression ret = ExpressionFactory.newInstance(exprList.get(0).eval(environment), new NilAtom());;
		for(int i = 1; i < exprList.size(); ++i) {
			ret = ExpressionFactory.newInstance(exprList.get(i).eval(environment), ret);
		}
		return ret; 
		
	}

}
