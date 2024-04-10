public class Player {
    private int score;
    private boolean setter;

    public Player(){
        this.score = 0;
        this.setter = true;

    }


    public void setScore(int score) {
        this.score = score;
    }

    public boolean isSetter() {
        return setter;
    }

    public void setSetter(boolean setter) {
        this.setter = setter;
    }
}
