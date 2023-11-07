import stanford.karel.Karel;

public class ExMakeTripleBeeperPilesOnStreet extends Karel {

        public void run() {
                for(int i = 0; i < 3; i++){
                        putBeeper();
                }
                while(frontIsClear()){
                        move();
                        for(int i = 0; i < 3; i++){
                                putBeeper();
                        }
                }
        }

        private void turnRight() {
                turnLeft();
                turnLeft();
                turnLeft();
        }

}
