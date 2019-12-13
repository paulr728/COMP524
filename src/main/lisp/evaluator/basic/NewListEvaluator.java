package main.lisp.evaluator.basic;

import java.util.List;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.parallel.args.ArgumentEvaluator;
import main.lisp.evaluator.parallel.args.ArgumentEvaluatorSingleton;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class NewListEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		List<SExpression> retList = ArgumentEvaluatorSingleton.get().evaluate(ArgumentEvaluator.split(expr), environment);
		SExpression ret = new NilAtom();
		for(int i = retList.size()-1; i >= 0 ; --i) {
			ret = ExpressionFactory.newInstance(retList.get(i), ret);
		}
		return ret;
	}

}
