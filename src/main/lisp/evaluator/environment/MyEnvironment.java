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
		Optional<SExpression> ret = super.getScope().get(id);
		if(ret == null) ret = super.getParent().lookup(id);
		return ret;
	}

	@Override
	public Optional<Function> lookupFun(IdentifierAtom id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assign(IdentifierAtom id, SExpression value) {
		// TODO Auto-generated method stub
		if(super.getParent() == null || super.getParent().lookup(id) == null)
			super.getScope().put(id, value);
		else
			super.getParent().assign(id, value);
	}

	@Override
	public void assignFun(IdentifierAtom id, Function value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Environment newChild() {
		// TODO Auto-generated method stub
		return new MyEnvironment(this);
	}

	@Override
	public Environment copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
