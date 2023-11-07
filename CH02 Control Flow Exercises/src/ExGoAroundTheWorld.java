import stanford.karel.Karel;

public class ExGoAroundTheWorld extends Karel {

        public void run() {
                if(frontIsBlocked()){
                        if(leftIsClear()) {
                                turnLeft();
                        }
                }
                while(frontIsClear()){
                      move();
                      while(frontIsBlocked()){
                              turnLeft();
                      }
                }
        }
}
