package main.lisp.evaluator.basic;

import java.util.ArrayList;
import java.util.List;

import main.LispInterpreterSettings;
import main.LispInterpreterSettings.EvaluationMode;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.parallel.args.AbstractArgumentEvaluator;
import main.lisp.evaluator.parallel.args.ArgumentEvaluator;
import main.lisp.evaluator.parallel.pool.BasicJoiner;
import main.lisp.evaluator.parallel.pool.SExpEvaluatorHelper;
import main.lisp.parser.terms.SExpression;
import main.util.parallel.Joiner;

public class EagerListEvaluator extends AbstractArgumentEvaluator {

	@Override
	public List<SExpression> evaluate(List<SExpression> arg0, Environment arg1) {
		// TODO Auto-generated method stub
		if(LispInterpreterSettings.getEvaluationMode() == EvaluationMode.NORMAL) {
			return evaluateSerial(arg0, arg1);
		}
		else if (LispInterpreterSettings.getEvaluationMode() == EvaluationMode.EAGER && !LispInterpreterSettings.isEagerPool()) {
			return evaluateParallelWithHelperClass(arg0, arg1);
		}
		return null;
	}

	@Override
	public List<SExpression> evaluateParallelWithHelperClass(List<SExpression> arg0, Environment arg1) {
		// TODO Auto-generated method stub
		if(arg0.size() == 0) return null;
		List<SExpression> ret = new ArrayList<SExpression>();
		Environment copyEnv = arg1.copy();
		Joiner joiner = new BasicJoiner(arg0.size());
		for(int i = 1; i < arg0.size(); ++i) {
			Thread t = new Thread(new SExpEvaluatorHelper(arg0, ret, i, joiner, copyEnv));
			t.setName(this.nextHelperClassEvalThreadName());
			t.start();
		}
		ret.set(0, arg0.get(0).eval(copyEnv));
		try {
			joiner.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<SExpression> evaluateParallelWithLambda(List<SExpression> arg0, Environment arg1) {
		// TODO Auto-generated method stub
		if(arg0.size() == 0) return null;
		List<SExpression> ret = new ArrayList<SExpression>();
		Environment copyEnv = arg1.copy();
		Joiner joiner = new BasicJoiner(arg0.size());
		for(int i = 1; i < arg0.size(); ++i) {
			final int k = i;
			Thread t = new Thread(()->{
				ret.set(k, arg0.get(k).eval(copyEnv));
			});
			t.setName(this.nextHelperClassEvalThreadName());
			t.start();
		}
		ret.set(0, arg0.get(0).eval(copyEnv));
		try {
			joiner.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<SExpression> evaluateParallelWithThreadPool(List<SExpression> arg0, Environment arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SExpression> evaluateSerial(List<SExpression> arg0, Environment arg1) {
		// TODO Auto-generated method stub
		List<SExpression> ret = new ArrayList<SExpression>();
		
		for(int i = 0; i < arg0.size(); ++i) {
			ret.add(arg0.get(i).eval(arg1));
		}
		return ret;
	}

}
