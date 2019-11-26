package main.lisp.evaluator.environment;

import java.util.Optional;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.function.Function;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.SExpression;

public class MyEnvironment extends AbstractEnvironment {

	public MyEnvironment() {
		this(null);
	}
	public MyEnvironment(MyEnvironment parent) {
		super(parent);
	}
	@Override
	public Optional<SExpression> lookup(IdentifierAtom id) {
		// TODO Auto-generated method stub
		Optional<SExpression> ret = this.getScope().get(id);
		if(ret.isEmpty() && this.getParent() != null) ret = this.getParent().lookup(id);
		return ret;
	}

	@Override
	public Optional<Function> lookupFun(IdentifierAtom id) {
		// TODO Auto-generated method stub
		Optional<Function> ret = this.getScope().getFun(id);
		if(ret.isEmpty() && this.getParent() != null) ret = this.getParent().lookupFun(id);
		return ret;
	}

	@Override
	public void assign(IdentifierAtom id, SExpression value) {
		// TODO Auto-generated method stub
		this.getScope().put(id, value);
//		if(this.getParent() == null || this.getParent().lookup(id).isEmpty())
//			
//		else
//			this.getParent().assign(id, value);
	}

	@Override
	public void assignFun(IdentifierAtom id, Function value) {
		// TODO Auto-generated method stub
		getScope().putFun(id, value);
	}

	@Override
	public Environment newChild() {
		// TODO Auto-generated method stub
		return new MyEnvironment(this);
	}

	@Override
	public Environment copy() {
		// TODO Auto-generated method stub
		return this;
	}

}
