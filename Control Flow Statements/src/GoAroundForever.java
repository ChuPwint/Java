import stanford.karel.Karel;

public class GoAroundForever extends Karel {
    public void run(){
            // A comment start with double slash like this
            // A comment is not part of the program execution
            // Karel will never get out of this loop
            while(frontIsClear()) {
                    move();
                    turnLeft();
            }

            putBeeper();
    }
}