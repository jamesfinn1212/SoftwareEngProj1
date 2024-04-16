public class Player {
    private int score; // Player's score
    private int raysPlaced = 0; // Number of rays placed by the player
    private boolean setter = true; // Flag indicating whether the player is a setter
    private boolean experimenter = !setter; // Flag indicating whether the player is an experimenter

    // Constructor to initialize the player's score
    public Player(){
        this.score = 0;
    }

    // Method to set the player's score
    public void setScore(int score) {
        this.score = score;
    }

    // Method to get the player's score
    public int getScore(){
        return this.score;
    }

    // Method to check if the player is a setter
    public boolean isSetter() {
        return setter;
    }

    // Method to check if the player is an experimenter
    public boolean isExperimenter() {
        return experimenter;
    }

    // Method to increment the counter for rays placed by the player
    public void incrementRayCounter() {
        raysPlaced++;
    }

    // Method to get the number of rays placed by the player
    public int getRaysPlaced() {
        return raysPlaced;
    }

    // Method to set whether the player is a setter or not
    public void setSetter(boolean setter) {
        this.setter = setter;
        this.experimenter = !setter; // Update experimenter flag accordingly
    }
}