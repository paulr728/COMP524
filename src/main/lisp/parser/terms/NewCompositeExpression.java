package main.lisp.parser.terms;

import main.lisp.evaluator.BuiltinOperationManagerSingleton;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.Lambda;

public class NewCompositeExpression extends NewBasicExpression {

	public NewCompositeExpression(SExpression head, SExpression tail) {
		super(head, tail);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SExpression eval(Environment environment) {
		// TODO Auto-generated method stub
		if(this.getHead() instanceof Lambda) {
			Evaluator e = BuiltinOperationManagerSingleton.get().getEvaluator("lambda");
			return e.eval(this.getTail(), environment);
		}
		else
			return super.eval(environment);
	}

}
