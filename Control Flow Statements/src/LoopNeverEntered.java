import stanford.karel.Karel;

public class LoopNeverEntered extends Karel {
    public void run(){
            // A comment start with double slash like this
            // A comment is not part of the program execution

            // This loop is never entered for default world
            // and its body will be totally skipped
            while(frontIsClear()) {
                    move();
                    turnLeft();
                    putBeeper();
            }
            // Commands below will be executed as usual
            turnLeft();
            move();
            putBeeper();
    }
}