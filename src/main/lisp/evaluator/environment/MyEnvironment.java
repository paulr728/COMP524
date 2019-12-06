package main.lisp.evaluator.environment;

import java.util.Optional;

import main.LispInterpreterSettings;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.function.Function;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class MyEnvironment extends AbstractEnvironment {

	public MyEnvironment() {
		this(null);
	}

	public MyEnvironment(MyEnvironment parent) {
		super(parent);
	}

	public MyEnvironment(Environment parent, CopyableScope scope) {
		super(parent, scope);
	}

	@Override
	public synchronized Optional<SExpression> lookup(IdentifierAtom id) {

		// TODO Auto-generated method stub
		Optional<SExpression> ret = this.getScope().get(id);
		if (ret.isEmpty() && this.getParent() != null)
			ret = this.getParent().lookup(id);
		return ret;

	}

	@Override
	public synchronized Optional<Function> lookupFun(IdentifierAtom id) {
		// TODO Auto-generated method stub

		Optional<Function> ret = this.getScope().getFun(id);
		if (ret.isEmpty() && this.getParent() != null)
			ret = this.getParent().lookupFun(id);
		return ret;

	}

	@Override
	public synchronized void assign(IdentifierAtom id, SExpression value) {
		// TODO Auto-generated method stub
//		this.getScope().put(id, value);

		if (value instanceof NilAtom || !this.getScope().get(id).isEmpty()) {
			// local scope
			this.getScope().put(id, value);
		} else if (this.getParent() == null) {
			this.getScope().put(id, value);
		} else {
			this.getParent().assign(id, value);
		}

	}

	@Override
	public synchronized void assignFun(IdentifierAtom id, Function value) {
		// TODO Auto-generated method stub

		getScope().putFun(id, value);

	}

	@Override
	public Environment newChild() {
		// TODO Auto-generated method stub

		return new MyEnvironment(this);

	}

	@Override
	public synchronized Environment copy() {
		// TODO Auto-generated method stub

		if (LispInterpreterSettings.isDeepCopyEnvironment()) {
			// deep logical copy
			Environment newParent = this.getParent();
			if (newParent == null)
				return new MyEnvironment(null, this.getScope().copy());
			else
				return new MyEnvironment(newParent.copy(), this.getScope().copy());

		}
		return this;

	}

}
