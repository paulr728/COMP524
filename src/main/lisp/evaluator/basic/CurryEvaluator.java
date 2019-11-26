package main.lisp.evaluator.basic;

import java.util.ArrayList;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.function.Lambda;
import main.lisp.evaluator.function.LambdaFactory;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;

public class CurryEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression arg0, Environment arg1) {
		// TODO Auto-generated method stub
		SExpression expr = arg0.getTail();
		if (expr instanceof NilAtom) {
			throw new IllegalStateException("Missing arguments for operator 'curry'");
		}
		Lambda l = (Lambda)(expr.getHead().eval(arg1));
		SExpression params = expr.getTail();
		IdentifierAtom[] argNames = l.getArgumentNames();
		IdentifierAtom[] lambdaArgs = null;
		SExpression lambdaBody = new NilAtom();
		int numEs = 0; 
		
		ArrayList<SExpression> EList = new ArrayList<SExpression>();
		while(!(params instanceof NilAtom)) {
			numEs++;
			SExpression listParam = new NilAtom();
			listParam = ExpressionFactory.newInstance(params.getHead().eval(arg1), listParam);
			listParam = ExpressionFactory.newInstance(new IdentifierAtom("QUOTE"),listParam);
			EList.add(listParam);
			//EList.add(params.getHead().eval(arg1));
			params = params.getTail();
		}
		
		if(numEs >= argNames.length) {
			throw new IllegalStateException("More arguments for operator 'curry'");
		}
		lambdaArgs = new IdentifierAtom [argNames.length - numEs];
		for(int i = argNames.length - 1; i >= numEs; --i) {
			lambdaBody = ExpressionFactory.newInstance(argNames[i], lambdaBody);
			lambdaArgs[i-numEs] = argNames[i];
		}
		for(int i = numEs-1; i >= 0; --i) {
			lambdaBody = ExpressionFactory.newInstance(EList.get(i), lambdaBody);
		}
		SExpression sl = ExpressionFactory.newInstance(l, new NilAtom());
		lambdaBody = ExpressionFactory.newInstance(ExpressionFactory.newInstance(new IdentifierAtom("QUOTE"), sl), lambdaBody);
		//lambdaBody = ExpressionFactory.newInstance(new IdentifierAtom("QUOTE"), lambdaBody);
		lambdaBody = ExpressionFactory.newInstance(new IdentifierAtom("FUNCALL"), lambdaBody);
		lambdaBody = ExpressionFactory.newInstance(lambdaBody, new NilAtom());
		return LambdaFactory.newInstance(lambdaArgs, lambdaBody);
		
	}

}

