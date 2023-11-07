import stanford.karel.Karel;

public class MoveBeeperToOtherSide extends Karel{
    public void run(){
        move();
        move();
        move();
        move();
        pickBeeper();
        turnLeft();
        move();
        move();
        turnLeft();
        turnLeft();
        turnLeft();
        move();
        turnLeft();
        turnLeft();
        turnLeft();
        move();
        move();
        putBeeper();
        turnLeft();
        move();
    }
}