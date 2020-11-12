/**
 * File: NodeMultipleEvents.java
 * Creation: 7 nov. 2020, Jean-Philippe.Prost@univ-amu.fr
 * Template étudiants
 */
package pracHDVELH;

import java.util.ArrayList;
import java.util.Scanner;

import myUtils.ErrorNaiveHandler;

/**
 * @author prost
 *
 */
public class Event extends NodeMultiple {
	public static final String ERROR_MSG_UNEXPECTED_END = "Sorry, for some unexpected reason the story ends here...";
	public static final String PROMPT_ANSWER = "Answer: ";
	public static final String WARNING_MSG_INTEGER_EXPECTED = "Please input a integer within range!";

	protected GUIManager gui;
	protected int playerAnswer;
	protected int id;
	protected ArrayList<Event> daughters;
	protected String data;
	protected int pathAnswer;

	static int lastId = 0;


	public Event(GUIManager gui, String data) {
		this.gui = gui;
		this.data = data;
		this.daughters = new ArrayList<>();

		this.id = lastId + 1;
		lastId = lastId + 1;
	}

	/**
	 * @return the playerAnswer
	 */
	public int getPlayerAnswer() {
		int playerAnswerV;
		while(true) {
			playerAnswerV = this.getReader().nextInt();
			if (!this.isInRange(playerAnswerV-1)) {
				this.gui.output(WARNING_MSG_INTEGER_EXPECTED);
				continue;
			}
			break;
		}
		this.playerAnswer = playerAnswerV;
		return this.interpretAnswer();
	}



	public boolean isFinal() {
		if (this.daughters.size() == 0) {
			return true;
		}
		return false;
	}

	public boolean isInRange(int index) {
		if (index >= 0 && index < this.daughters.size()) {
			return true;
		}
		return false;
	}


	public int interpretAnswer() {
		if (this.daughters.get(this.playerAnswer-1) ==  null) {
			this.gui.outputErr(ERROR_MSG_UNEXPECTED_END);
		}

		this.pathAnswer = this.playerAnswer-1;
		return this.pathAnswer;
	}


	/**
	 * @param playerAnswer the playerAnswer to set
	 */
	public void setPlayerAnswer(int playerAnswer) {
		this.playerAnswer = playerAnswer;
	}

	/**
	 * @return the reader
	 */
	public Scanner getReader() {
		return this.gui.getInputReader();
	}

	/**
	 * @param reader the reader to set
	 */
	public void setReader(Scanner reader) {
		this.gui.setInputReader(reader);
	}

	/**
	 * @return the chosenPath
	 */
	public int getChosenPath() {
		return this.pathAnswer;
	}

	/**
	 * @param chosenPath the chosenPath to set
	 */
	public void setChosenPath(int chosenPath) {
		this.pathAnswer = pathAnswer;
	}

	/* Methods */
	/**
	 * @see pracHDVELH.NodeMultiple#getData()
	 */
	public String getData() {
		return this.data;
	}

	/**
	 * @see pracHDVELH.NodeMultiple#setData(Object)
	 * @param data
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @see pracHDVELH.NodeMultiple#getDaughter(int)
	 */
	@Override
	public Event getDaughter(int i) {
		return this.daughters.get(i);
	}

	/**
	 * @see pracHDVELH.NodeMultiple#setDaughter(NodeMultiple, int)
	 * @param daughter
	 * @param i
	 */
	public void setDaughter(Event daughter, int i) {
		this.daughters.set(i, daughter);
	}


	public void addDaughter(Event daughter) {
		this.daughters.add(daughter);
	}

	/**
	 * @return the gui
	 */
	public GUIManager getGui() {
		return this.gui;
	}

	/**
	 * @param gui the gui to set
	 */
	public void setGui(GUIManager gui) {
		this.gui = gui;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	public void setId() {
		this.id = id;
	}


	public Event run() {
		this.gui.outputln(this.getData());
		this.gui.outputln(this.PROMPT_ANSWER);

		// on récupère le choix
		int userChoice = this.getPlayerAnswer();
		
		return this.getDaughter(userChoice);
	}

}

// eof