import stanford.karel.Karel;

public class CollectNewspaperKarel extends Karel {

        public void run() {
                collectNewspaper();
        }
        void collectNewspaper(){
                goPickNewspaper();
                returnToPosition();
        }
        void returnToPosition(){
                goInside();
                turnRight();
                goToWall();
                turnLeft();
                goToWall();
                while(notFacingEast()) {
                        turnLeft();
                }
        }
        void goInside(){
                while(notFacingWest()){
                        turnLeft();
                }
                move();
        }
        void goPickNewspaper(){
                findDoor();
                move();
                pickBeeper();
        }
        void findDoor(){
                goToWall();
                turnRight();
                while(leftIsBlocked()){
                        move();
                }
                turnLeft();
        }
        void goToWall(){
                while (frontIsClear()) {
                        move();
                }
        }
        void turnRight(){
                for(int i = 0; i < 3; i++){
                        turnLeft();
                }
        }
}
