package env;

import java.util.logging.Logger;

import javax.swing.JOptionPane;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import jason.environment.Environment;

public class QuizEnv2 extends Environment {

	private Logger logger = Logger.getLogger("exemplo.mas2j."+QuizEnv2.class.getName());
	private QuizGui quizGui;
	private Term uri;
	private Term predicate;
	
	public QuizEnv2() {
		quizGui = new QuizGui(this);
	}
	
	@Override
	public void init(String[] args) {
		super.init(args);
	}
	
	@Override
	public boolean executeAction(String agName, Structure act) {
		if (act.getFunctor().equals("make_question")) {
			Term question = act.getTerm(0);
			this.uri = act.getTerm(1);
			this.predicate = act.getTerm(2);
			
			quizGui.updateQuestion(question.toString());
			
			return true;
		} else if (act.getFunctor().equals("show_result")) {
			Term res = act.getTerm(0);
			String resStr = res.toString().replaceAll("\"", "");
			if (resStr.contains("^^")) {
				resStr = resStr.substring(0, resStr.indexOf("^^"));
			}
			if (resStr.length() > 200) {
				int width = 200;
				while (width < resStr.length()) {
					resStr = resStr.substring(0, width) + "\n" + 
				    resStr.substring(width, resStr.length());
					width += 200;
				}
			}
			JOptionPane.showMessageDialog(null, resStr);
			return true;
		} else {
			return false;
		}
	}
	
	public void registerAnswer(String answer) {
		addPercept(Literal.parseLiteral("question_response(\""+
				answer+"\", " + uri.toString() + 
				", " + predicate.toString() + ")"));
	}
	
	@Override
	public void stop() {
		super.stop();
	}
	
}
