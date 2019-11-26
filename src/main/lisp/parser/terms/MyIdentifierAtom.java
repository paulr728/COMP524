package main.lisp.parser.terms;

import main.lisp.scanner.tokens.Token;

import main.lisp.evaluator.Environment;

public class MyIdentifierAtom extends IdentifierAtom {

	public MyIdentifierAtom(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}
	public MyIdentifierAtom(Token token) {
		super(token);
	}

	@Override
	public SExpression eval(Environment environment) {
		// TODO Auto-generated method stub
		if(environment.lookup(this) == null)
			return super.eval(environment);
		else
			return environment.lookup(this).get();
	}
	

}
