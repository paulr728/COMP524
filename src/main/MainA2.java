package main;

import main.lisp.evaluator.CustomOperationRegisterer;
import main.lisp.evaluator.environment.EnvironmentFactory;
import main.lisp.evaluator.environment.MyEnvironment;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.IdentifierAtomFactory;
import main.lisp.parser.terms.MyIdentifierAtom;
import main.lisp.parser.terms.NewCompositeExpression;

public class MainA2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomOperationRegisterer.registerAll();
		ExpressionFactory.setClass(NewCompositeExpression.class);
		EnvironmentFactory.setClass(MyEnvironment.class);
		IdentifierAtomFactory.setClass(MyIdentifierAtom.class);
		Main.main(args);
	}
}
