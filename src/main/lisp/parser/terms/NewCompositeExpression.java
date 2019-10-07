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
		// lambda declaration
		if(!this.getHead().isList() && super.eval(environment) instanceof Lambda) {
			return super.eval(environment);
		}
		// lambda evaluation
		else if(this.getHead().isList()) {
			SExpression headAtom = this.getHead().eval(environment);
			if(!(headAtom instanceof Lambda)) {
				return headAtom;
			}
			SExpression realParams = this.getTail();
			if(realParams instanceof NilAtom) {
				throw new IllegalStateException("Missing arguments for operator 'lambda' evaluation");
			}
			Environment childEnv = environment.newChild();
			IdentifierAtom[] formalParams = ((Lambda) headAtom).getArgumentNames();
			for(int i = 0; i < formalParams.length; ++i) {
				childEnv.assign(formalParams[i], realParams.getHead().eval(environment));
				realParams = realParams.getTail();
			}
			if(!(realParams instanceof NilAtom)) {
				throw new IllegalStateException("Too many arguments for 'lambda' evaluation");
			}
			
			return ((Lambda)headAtom).eval(childEnv);
		}
		else
			return super.eval(environment);
	}

}
