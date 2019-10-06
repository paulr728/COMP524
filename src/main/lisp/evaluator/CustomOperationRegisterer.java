package main.lisp.evaluator;

import main.lisp.evaluator.basic.AndEvaluator;
import main.lisp.evaluator.basic.CondEvaluator;
import main.lisp.evaluator.basic.EvalEvaluator;
import main.lisp.evaluator.basic.GEqEvaluator;
import main.lisp.evaluator.basic.GreaterThanEvaluator;
import main.lisp.evaluator.basic.LEqEvaluator;
import main.lisp.evaluator.basic.LambdaEvaluator;
import main.lisp.evaluator.basic.LessThanEvaluator;
import main.lisp.evaluator.basic.ListEvaluator;
import main.lisp.evaluator.basic.LoadEvaluator;
import main.lisp.evaluator.basic.NotEvaluator;
import main.lisp.evaluator.basic.OrEvaluator;
import main.lisp.evaluator.basic.QuoteEvaluator;
import main.lisp.evaluator.basic.SetqEvaluator;

public class CustomOperationRegisterer extends BasicOperationRegisterer {

	public static void registerAll() {
		BasicOperationRegisterer.registerAll();
		BuiltinOperationManagerSingleton.get().registerEvaluator("list", new ListEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("quote", new QuoteEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("eval", new EvalEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("load", new LoadEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("cond", new CondEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("and", new AndEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("or", new OrEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("not", new NotEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("<", new LessThanEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator(">", new GreaterThanEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("<=", new LEqEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator(">=", new GEqEvaluator());
		
		// A2
		BuiltinOperationManagerSingleton.get().registerEvaluator("setq", new SetqEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("lambda", new LambdaEvaluator());
	}
}
