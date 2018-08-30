package env;

import java.util.logging.Logger;

import javax.swing.JOptionPane;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import jason.environment.Environment;

public class QuizEnv extends Environment {

	private Logger logger = Logger.getLogger("exemplo.mas2j."+QuizEnv.class.getName());
	
	@Override
	public void init(String[] args) {}
	
	@Override
	public boolean executeAction(String agName, Structure act) {
		if (act.getFunctor().equals("make_question")) {
			Term question = act.getTerm(0);
			Term uri = act.getTerm(1);
			Term predicate = act.getTerm(2);
			
			String response = JOptionPane.showInputDialog(question.toString());
			
			addPercept(Literal.parseLiteral("question_response(\""+
					response+"\", " + uri.toString() + 
					", " + predicate.toString() + ")"));
			
			return true;
		} else {
			logger.info("No response was sent!");
			return false;
		}
	}
	
	//Obtém o termo passado por parâmetro
	//Realiza a questão ao aluno
	//Chama a ação que recebe a resposta e devolve ao QuestionAgent.
	
	@Override
	public void stop() {
		super.stop();
	}
	
}
