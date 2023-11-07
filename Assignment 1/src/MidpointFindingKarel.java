import stanford.karel.Karel;

public class MidpointFindingKarel extends Karel {

        public void run() {
                checkMidPoint();
                println("Done!")
        }

        void checkMidPoint() {
                putBeeper();
                placements();
                turnAround();
                putAnotherSpace();
                while(notFacingSouth()){
                        putAnotherSpace();
                }
        }
        //consider beepers as edges
        //place them in each wall
        void placements(){
                while(frontIsClear()){
                        move();
                }
                putBeeper();
        }
        //after picking Beeper
        // to move or not checking
        void putAnotherSpace(){
                if (beepersPresent()) {
                        westOrEast();
                }
        }
        void westOrEast(){
                if(facingWest()){
                        westEastCommand();
                        turnAround();
                }
                else{
                        westEastCommand();
                }
        }
        void westEastCommand(){
                if(frontIsClear()){
                        checkBeeper();
                        mid();
                        moveTillWall();
                }
                else{
                        pickBeeper();
                        turnAround();
                        move();
                }
        }
        void moveTillWall(){
                while(noBeepersPresent()){
                        //when not mid yet
                        if(notFacingSouth()){
                                move();
                        }
                }
                if(notFacingWest()){
                        turnAround();
                }
        }
        void checkBeeper(){
                if(beepersPresent()){
                        pickBeeper();
                        move();
                }
                putBeeper();
                move();
        }
        //to stop when find mid
        void mid(){
                if(beepersPresent()){
                        pickBeeper();
                        turnAround();
                        move();
                        turnRight();
                }
        }
        void turnAround() {
                if (frontIsBlocked()) {
                        turnLeft();
                        turnLeft();
                }else{
                        //for when reach beeper Edge condition
                        //frontClear but beeper
                        turnLeft();
                        turnLeft();
                }
        }
        void turnRight(){
                for(int i = 0; i < 3; i++){
                        turnLeft();
                }
        }
}

