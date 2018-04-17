package entities;

public class Crusade
{
    private String popeName, goal;
    private boolean haveIndulgence, isAve, isDeus;


    public String isHaveIndulgence() {
        return String.valueOf(haveIndulgence);
    }

    public void setHaveIndulgence(boolean haveIndulgence) {
        this.haveIndulgence = haveIndulgence;
    }

    public String isAve() {
        return String.valueOf(isAve);
    }

    public void setAve(boolean ave) {
        isAve = ave;
    }

    public String isDeus() {
        return String.valueOf(isDeus);
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
        return "Крестовый Поход:" +"\r\n"+
                "Имя Папы Римского: " + popeName +"\r\n"+
                "Причина похода:  " + goal  +"\r\n"+
                "Отпущены ли грехи:  " + haveIndulgence +"\r\n"+
                "AVE MARIA?: " + isAve +"\r\n"+
                "DEUS VULT?: " + isDeus +"\r\n";
    }
}
