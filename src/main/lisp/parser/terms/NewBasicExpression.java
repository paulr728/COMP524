package main.lisp.parser.terms;

import main.lisp.evaluator.BuiltinOperationManagerSingleton;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;

public class NewBasicExpression extends BasicExpression {

	private final SExpression head;
	private final SExpression tail;
	
	public NewBasicExpression(SExpression head, SExpression tail) {
		super(head, tail);
		// TODO Auto-generated constructor stub
		this.head = head;
		this.tail = tail;
	}

	@Override
	public boolean isList() {
		if(this.tail instanceof NilAtom) {
			return true;
		}
		else
		{
			return this.tail.isList();
		}
	}

	@Override
	public String toStringAsList() {
		if(this.getTail() instanceof NilAtom) {
			return this.getHead().toString();
		}
		return this.getHead().toString() + " " + this.getTail().toStringAsList();
	}

	@Override
	public String toStringAsSExpression() {
		return "(" + this.getHead().toString() + " " + "." + " " + this.getTail().toString() + ")";
	}
	
	@Override
	public String toStringAsSExpressionDeep() {
		
		return "(" + this.getHead().toStringAsSExpressionDeep() + " " + "." + " " + 
		this.getTail().toStringAsSExpressionDeep()
				+ ")";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		boolean isL = this.isList();
		return isL ? "(" + this.toStringAsList() + ")" : this.toStringAsSExpression();
	}
	
}
