package main.lisp.evaluator.basic;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.interpreter.InterpreterModel;
import main.lisp.interpreter.InterpreterModelSingleton;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.StringAtom;
import main.lisp.parser.terms.TAtom;

import java.util.List;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadEvaluator implements Evaluator {

	@Override
	public SExpression eval(SExpression expr, Environment environment) {
		// TODO Auto-generated method stub
		expr = expr.getTail();
		if(expr instanceof NilAtom ) {
			throw new IllegalStateException("Missing arguments for operator 'load'");
		}
		if(!(expr.getTail() instanceof NilAtom)) {
			throw new IllegalStateException("Too many arguments for operator 'load'");
		}
		
		expr = expr.getHead();
		
		if (!(expr instanceof StringAtom)) {
			return new NilAtom();
		}
		try {
			StringAtom s = (StringAtom)expr;
			String fileName = s.getValue();
			Path filePath = Paths.get(fileName);
			List<String> lines = Files.readAllLines(filePath, Charset.defaultCharset());
			InterpreterModel interpreter = InterpreterModelSingleton.get();
			for(int i = 0; i < lines.size(); ++i  ) {
				interpreter.newInput(lines.get(i));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new NilAtom();
		}
		//System.out.println("Finish load operation");
		return new TAtom();
	}

}
