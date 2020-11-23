package pracHDVELH;

public class EventExactSolution extends Event {

    private String exactSolution;


    public EventExactSolution(GUIManager gui, String data, String exactSolution) {
        super(gui, data);
        this.exactSolution = exactSolution;
    }


    @Override
    public int interpretAnswer() {
        boolean isStart = this.exactSolution.startsWith(this.getPlayerAnswer());
        if (isStart) {
            return 0;
        }
        return 1;
    }
}
