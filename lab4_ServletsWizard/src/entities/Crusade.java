package entities;

public class Crusade
{
    private String popeName, goal;
    private boolean haveIndulgence, isAve, isDeus;


    public boolean isHaveIndulgence() {
        return haveIndulgence;
    }

    public void setHaveIndulgence(boolean haveIndulgence) {
        this.haveIndulgence = haveIndulgence;
    }

    public boolean isAve() {
        return isAve;
    }

    public void setAve(boolean ave) {
        isAve = ave;
    }

    public boolean isDeus() {
        return isDeus;
    }

    public void setDeus(boolean deus) {
        isDeus = deus;
    }

    public Crusade()
    {
        super();
    }

    public String getPopeName() {
        return popeName;
    }

    public void setPopeName(String popeName) {
        this.popeName = popeName;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "Crusade{" +
                "popeName='" + popeName + '\'' +
                ", goal='" + goal + '\'' +
                ", haveIndulgence=" + haveIndulgence +
                ", isAve=" + isAve +
                ", isDeus=" + isDeus +
                '}';
    }
}
