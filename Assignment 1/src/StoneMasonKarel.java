import stanford.karel.Karel;

public class StoneMasonKarel extends Karel {

        public void run() {
                fillAllColumn();
        }
        void fillAllColumn(){
                searchAndFill();
                comeDown();
                while(frontIsClear()) {
                        findNextColumn();
                        searchAndFill();
                        comeDown();
                }
        }
        void searchAndFill(){
                turnLeft();
                while(frontIsClear()){
                        fillBlock();
                }
                if(noBeepersPresent()){
                        putBeeper();
                }
        }
        void comeDown(){
                while(notFacingSouth()){
                        turnLeft();
                }
                while(frontIsClear()){
                        move();
                }
                turnLeft();
        }
        void fillBlock(){
                if(noBeepersPresent()){
                        putBeeper();
                        move();
                }else{
                        move();
                }
        }
        void findNextColumn(){
                for(int i = 0; i < 4; i++){
                        move();
                }
        }
}
