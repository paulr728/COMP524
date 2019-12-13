package main;

import java.util.Scanner;

import main.lisp.distributed.ServeClientRunnable;
import main.lisp.distributed.ServerModel;
import main.lisp.evaluator.BasicOperationRegisterer;
import main.lisp.evaluator.BuiltInLazyOperationRegisterer;
import main.lisp.evaluator.CustomOperationRegisterer;
import main.lisp.evaluator.UtilityOperationRegisterer;
import main.lisp.evaluator.basic.EagerListEvaluator;
import main.lisp.evaluator.environment.EnvironmentFactory;
import main.lisp.evaluator.environment.MyEnvironment;
import main.lisp.evaluator.parallel.args.ArgumentEvaluatorSingleton;
import main.lisp.interpreter.InterpreterModel;
import main.lisp.interpreter.InterpreterModelFactory;
import main.lisp.interpreter.InterpreterModelSingleton;
import main.lisp.interpreter.InterpreterViewSingleton;
import main.lisp.interpreter.ObservableLispInterpreterWithEnvironmentAndLazyEvaluation;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.IdentifierAtomFactory;
import main.lisp.parser.terms.MyIdentifierAtom;
import main.lisp.parser.terms.NewCompositeExpression;
import main.util.parallel.SystemOutReplacingTeeOutputStream;
import main.util.parallel.TeeOutputStream;

public class Server {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String aHostName = "localhost";
		int portNumber = args.length > 0 ? Integer.parseInt(args[0]) : 9001;
//		int portNumber = 9001;
		BasicOperationRegisterer.registerAll();
		UtilityOperationRegisterer.registerAll();
		BuiltInLazyOperationRegisterer.registerAll();
		
		CustomOperationRegisterer.registerAll();
		ExpressionFactory.setClass(NewCompositeExpression.class);
		EnvironmentFactory.setClass(MyEnvironment.class);

		IdentifierAtomFactory.setClass(MyIdentifierAtom.class);
		ArgumentEvaluatorSingleton.setClass(EagerListEvaluator.class);
		// set interpreter model to controller in server
		InterpreterModelFactory.setClass(ObservableLispInterpreterWithEnvironmentAndLazyEvaluation.class);

		InterpreterModel interpreter = InterpreterModelSingleton.get();
		interpreter.registerPropertyChangeListener(InterpreterViewSingleton.get());

		// serve clients
		ServerModel server = new ServerModel(aHostName, portNumber);
		server.setInterpreter(interpreter);
		server.startServe();
	}

}
