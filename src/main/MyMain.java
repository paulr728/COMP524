package main;

import main.lisp.evaluator.CustomOperationRegisterer;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.NewBasicExpression;

public class MyMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomOperationRegisterer.registerAll();
		ExpressionFactory.setClass(NewBasicExpression.class);
		
		Main.main(args);
	}

}
