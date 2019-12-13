package main.lisp.evaluator.parallel.pool;
import java.util.List;

import main.lisp.evaluator.Environment;
import main.lisp.parser.terms.SExpression;
import main.util.parallel.Joiner;

public class SExpEvaluatorHelper implements Runnable {

	private List<SExpression> e;
	private SExpression[] e_eval;
	private int iExp;
	private Joiner joiner;
	private Environment env;
	public SExpEvaluatorHelper(List<SExpression> _e, SExpression[] _e_eval, int _iExp, Joiner _joiner, Environment _env) {
		e = _e;
		e_eval = _e_eval;
		iExp = _iExp;
		joiner = _joiner;
		env = _env;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.e_eval[iExp] = this.e.get(iExp).eval(env);
		this.joiner.finished();

	}

}
