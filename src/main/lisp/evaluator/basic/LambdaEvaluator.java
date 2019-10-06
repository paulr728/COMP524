package main.lisp.evaluator.basic;

import java.util.ArrayList;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.LambdaFactory;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.IdentifierAtomFactory;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.scanner.tokens.StringToken;
import main.lisp.scanner.tokens.Token;
import main.lisp.scanner.tokens.TokenFactory;
import main.lisp.scanner.tokens.TokenType;
public class LambdaEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		if(expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'lambda'");
		}
		IdentifierAtom[] argNames = null;
		SExpression body = null;
		
		SExpression params = expr.getHead();
		body = expr.getTail();
		ArrayList<SExpression> listParams = new ArrayList<SExpression>();
		listParams.add(params.getHead());
		while(!(params.getTail() instanceof NilAtom )) {
			params = params.getTail();
			listParams.add(params.getHead());
		}
		
		argNames = new IdentifierAtom [listParams.size()];
		TokenFactory.setTokenClass(TokenType.STRING, StringToken.class);
		for(int i = 0; i < listParams.size(); ++i) {
			Token t = TokenFactory.newInstance(TokenType.STRING, listParams.get(i).toString());
			argNames[i] = IdentifierAtomFactory.newInstance(t);
		}
		
		return LambdaFactory.newInstance(argNames, body);
	}

}
