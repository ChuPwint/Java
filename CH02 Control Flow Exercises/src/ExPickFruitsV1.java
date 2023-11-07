import stanford.karel.Karel;

public class ExPickFruitsV1 extends Karel {
        public void run() {
                while(frontIsClear()){
                        move();
                }
                turnLeft();
                while(rightIsBlocked()){
                        move();
                        if(beepersPresent()){
                                pickBeeper();
                        }
                }
                while(rightIsClear()){
                        turnRight();
                        move();
                        if(beepersPresent()){
                                pickBeeper();
                        }
                }
                while(frontIsClear()){
                        move();
                }
                if(frontIsBlocked()){
                        turnLeft();
                        move();
                }
        }

        private void turnRight() {
                turnLeft();
                turnLeft();
                turnLeft();
        }
}
