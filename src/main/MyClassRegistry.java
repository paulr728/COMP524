package main;

import main.lisp.evaluator.CustomOperationRegisterer;
import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.evaluator.OperationRegisterer;
import main.lisp.evaluator.basic.*;
import main.lisp.evaluator.environment.MyEnvironment;
import main.lisp.parser.terms.IdentifierAtom;
import main.lisp.parser.terms.NewCompositeExpression;
import main.lisp.parser.terms.NewBasicExpression;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.MyIdentifierAtom;
public class MyClassRegistry implements ClassRegistryA2{

	@Override
	public Class<?> getMain() {
		// TODO Auto-generated method stub
		return MainA2.class;
	}

	@Override
	public Class<? extends OperationRegisterer> getCustomOperationRegisterer() {
		// TODO Auto-generated method stub
		return CustomOperationRegisterer.class;
	}

	@Override
	public Class<? extends SExpression> getStringFormattingSExpression() {
		// TODO Auto-generated method stub
		return NewBasicExpression.class;
	}

	@Override
	public Class<? extends Evaluator> getQuoteEvaluator() {
		// TODO Auto-generated method stub
		return QuoteEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getEvalEvaluator() {
		// TODO Auto-generated method stub
		return EvalEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getLoadEvaluator() {
		// TODO Auto-generated method stub
		return LoadEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getCondEvaluator() {
		// TODO Auto-generated method stub
		return CondEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getListEvaluator() {
		// TODO Auto-generated method stub
		return ListEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getLTEvaluator() {
		// TODO Auto-generated method stub
		return LessThanEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getGTEvaluator() {
		// TODO Auto-generated method stub
		return GreaterThanEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getLTEEvaluator() {
		// TODO Auto-generated method stub
		return LEqEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getGTEEvaluator() {
		// TODO Auto-generated method stub
		return GEqEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getAndEvaluator() {
		// TODO Auto-generated method stub
		return AndEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getOrEvaluator() {
		// TODO Auto-generated method stub
		return OrEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getNotEvaluator() {
		// TODO Auto-generated method stub
		return NotEvaluator.class;
	}

	@Override
	public Class<? extends IdentifierAtom> getIdentifierAtomWithLookup() {
		// TODO Auto-generated method stub
		return MyIdentifierAtom.class;
	}

	@Override
	public Class<? extends SExpression> getLambdaCallingSExpression() {
		// TODO Auto-generated method stub
		return NewCompositeExpression.class;
	}

	@Override
	public Class<? extends Evaluator> getLambdaEvaluator() {
		// TODO Auto-generated method stub
		return LambdaEvaluator.class;
	}

	@Override
	public Class<? extends Environment> getNestedLexicalEnvironment() {
		// TODO Auto-generated method stub
		return MyEnvironment.class;
	}

	@Override
	public Class<? extends Evaluator> getSetqEvaluator() {
		// TODO Auto-generated method stub
		return SetqEvaluator.class;
	}

	@Override
	public Class<? extends OperationRegisterer> getAdditionalStatefulOperationRegisterer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends Evaluator> getDefunEvaluator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends Evaluator> getFuncallEvaluator() {
		// TODO Auto-generated method stub
		return FuncallEvaluator.class;
	}

	@Override
	public Class<? extends Evaluator> getFunctionEvaluator() {
		// TODO Auto-generated method stub
		return null;
	}

}
