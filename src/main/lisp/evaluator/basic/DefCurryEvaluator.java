package main.lisp.evaluator.basic;

import java.util.ArrayList;
import java.util.List;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.Function;
import main.lisp.evaluator.function.FunctionFactory;
import main.lisp.evaluator.function.Lambda;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class DefCurryEvaluator implements Evaluator{

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		IdentifierAtom g = (IdentifierAtom)expr.getHead();
		List<SExpression> body = new ArrayList<SExpression>();
		SExpression params = expr.getTail();
		while(!(params instanceof NilAtom)) {
			body.add(params.getHead());
			params = params.getTail();
		}
		
		SExpression newList = new NilAtom();
		for(int i= body.size() - 1; i >= 0; --i) {
			newList = ExpressionFactory.newInstance(body.get(i), newList);
		}
		newList = ExpressionFactory.newInstance(new IdentifierAtom("CURRY"), newList);
		SExpression generatedLambda = newList.eval(environment);
		Function l = FunctionFactory.newInstance((Lambda) generatedLambda, environment);
		environment.assignFun(g, l);
		return g;
	}

}
