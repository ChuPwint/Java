import acm.program.ConsoleProgram;

public class PineTree_Ex extends ConsoleProgram {
    public void run() {
        //setFont("JetBrains Mono NL-26");
        int width_1 = readInt("Enter an odd integer greater than or equal to 5 for " +
                                "\nTree width: ");
        if((width_1 % 2 == 0) || (width_1 < 5)){
            print("Please check the statement and re-enter value!\n");
            width_1 = readInt("Enter an odd integer greater than or equal to 5 for " +
                    "\nTree width: ");
        }

        int width_2 = readInt("Enter an odd integer greater than or equal to 3 " +
                                "and less than tree width\nTrunk width: ");
        if((width_2 > width_1) || (width_2 % 2 == 0)){
            print("Please enter a possible value for trunk width!\n");
            width_2 = readInt("Enter an odd integer greater than or equal to 3 " +
                    "and less than tree width\nTrunk width: ");
        }

        int height = readInt("Trunk height: ");

        int starCount = 1;
        for(int i = width_1; i > 0; i = i - 2){
            int spaceCount = (i / 2);
            for(int j = 0; j < spaceCount; j++){
                print(" ");
            }

            for(int k = 0; k < starCount; k++){
                print("*");
            }
            starCount += 2;
            println();
        }

        for(int m = 0; m < height; m++){
            int count = 1;
            if(width_2 > 3){
                int k = width_2;
                while(k != 3){
                    k -= 2;
                    count++;
                }
            }
            int trunkSpace = (width_1 / 2) - count;
            for(int n = 0; n < trunkSpace; n++){
                print(" ");
            }
            for(int l = 0; l < width_2; l++){
                print("*");
            }
            println();
        }
    }
}
