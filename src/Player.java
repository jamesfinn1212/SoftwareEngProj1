public class Player {
    private int score;
    private int raysPlaced = 0;
    private boolean setter = true;
    private boolean experimenter = !setter;

    public Player(){
        this.score = 0;
    }


    public void setScore(int score) {
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }


    public boolean isSetter() {
        return setter;
    }
    public boolean isExperimenter() {return experimenter;}

    public void incrementRayCounter() {
        raysPlaced++;
    }
    public int getRaysPlaced() {
        return raysPlaced;
    }

    public void setSetter(boolean setter) {
        this.setter = setter;
    }
}
