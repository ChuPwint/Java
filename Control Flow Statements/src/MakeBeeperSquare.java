import stanford.karel.Karel;

public class MakeBeeperSquare extends Karel{
        public void run(){
                for(int i = 0; i < 4; i++) {
                        putBeeper();
                        move();
                        turnLeft();
                }
                move();
        }
}
