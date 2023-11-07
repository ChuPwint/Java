import stanford.karel.Karel;

public class MakeBeeperRow extends Karel {
    public void run(){
            while(frontIsClear()){
                    putBeeper();
                    move();
            }
            putBeeper();
    }
}