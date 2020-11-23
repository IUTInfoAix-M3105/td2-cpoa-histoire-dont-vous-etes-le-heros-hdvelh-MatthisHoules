/**
 * File: NodeMultipleEvents.java
 * Creation: 7 nov. 2020, Jean-Philippe.Prost@univ-amu.fr
 * Template étudiants
 */
package pracHDVELH;

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
	protected int id;
	protected Event[] daughters;
	protected int pathAnswer;
	protected String playerAnswer;

	static int lastId = 0;

	@Override
	public String toString() {
		return super.toString();
	}

	public Event(GUIManager gui, String data) {
		this.gui = gui;
		this.data = data;
		this.daughters = new Event[NODE_MAX_ARITY];

		this.id = lastId + 1;
		lastId = lastId + 1;
	}

	/**
	 * @return the playerAnswer
	 */
	public String getPlayerAnswer() {
		String playerAnswerV;
		playerAnswerV = this.getReader().next();

		this.playerAnswer = playerAnswerV;

		return this.playerAnswer;
	}


	public int interpretAnswer() {
		int playerAnswerInt;
		this.gui.outputln(this.PROMPT_ANSWER);

		playerAnswerInt = Integer.parseInt(this.getPlayerAnswer());


		while(!this.isInRange(playerAnswerInt-1)) {
			this.gui.output(WARNING_MSG_INTEGER_EXPECTED);
			this.gui.outputln(this.PROMPT_ANSWER);

			playerAnswerInt = Integer.parseInt(this.getPlayerAnswer());

		}

		this.pathAnswer = playerAnswerInt-1;
		return this.pathAnswer;
	}



	public boolean isFinal() {
		// un evenement final ne possède pas de fille (renvoie vraie quand has daughters est false)
		return !this.hasDaughters();
	}

	public boolean hasDaughters() {
		int i = 0;
		while (i < daughters.length) {
			if (daughters[i] != null) return true;
			i = i+1;
		}
		return false;
	}

	public boolean isInRange(int index) {
		if (index >= 0 && index < this.daughters.length && this.daughters[index] != null) {
			return true;
		}
		return false;
	}


	/**
	 * @param playerAnswer the playerAnswer to set
	 */
	public void setPlayerAnswer(String playerAnswer) {
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
		return this.data.toString();
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
		if (i<0 || i >= NODE_MAX_ARITY) {
			ErrorNaiveHandler.abort(ERROR_MSG_INDEX_OUT_OF_RANGE+'@'+getClass()+".getDaughter()");
		}
		return this.daughters[i];
	}

	/**
	 * @see pracHDVELH.NodeMultiple#setDaughter(NodeMultiple, int)
	 * @param daughter
	 * @param i
	 */
	public void setDaughter(Event daughter, int i) {
		if (i < 0 || i >= NODE_MAX_ARITY) {
			ErrorNaiveHandler.abort(ERROR_MSG_INDEX_OUT_OF_RANGE+'@'+getClass()+".getDaughter()");
		}
		this.daughters[i] = daughter;
	}


	public void addDaughter(Event daughter) {
		if (daughter == null) return;
		int i = 0;
		while (i < daughters.length && daughters[i] != null) {
			i = i+1;
		}
		if (i != daughters.length) {
			daughters[i] = daughter;
		}
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

		int userChoice = this.interpretAnswer();

		return this.getDaughter(userChoice);
	}

}

// eof