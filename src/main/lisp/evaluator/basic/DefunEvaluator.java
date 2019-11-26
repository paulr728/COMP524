package main.lisp.evaluator.basic;

import java.util.ArrayList;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.Function;
import main.lisp.evaluator.function.FunctionFactory;
import main.lisp.evaluator.function.Lambda;
import main.lisp.evaluator.function.LambdaFactory;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class DefunEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		SExpression fName = expr.getHead();
		SExpression remaining = expr.getTail();
		SExpression pList = remaining.getHead();
		SExpression eList  = remaining.getTail();
		
		ArrayList<SExpression> pArray = new ArrayList<SExpression>();
		while(!(pList instanceof NilAtom)) {
			pArray.add(pList.getHead());
			pList = pList.getTail();
		}
		IdentifierAtom[] params = new IdentifierAtom[pArray.size()];
		for(int i = 0; i < pArray.size(); ++i) {
			params[i] = (IdentifierAtom) pArray.get(i);
		}
		
		Lambda l = LambdaFactory.newInstance(params, eList);
		Function f = FunctionFactory.newInstance(l, environment);
		environment.assignFun((IdentifierAtom) fName, f);
		return f;
	}

}
