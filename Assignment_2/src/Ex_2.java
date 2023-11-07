import acm.program.ConsoleProgram;

public class Ex_2 extends ConsoleProgram {
    public void run() {
        //setFont("JetBrains Mono NL-26");
        int rowNum = readInt("Enter width of diamond: ");
        while(rowNum < 5 || rowNum % 2 == 0) {
            println("Please enter an odd number greater than 5.");
            rowNum = readInt("Enter width of diamond: ");
        }

        int stopPoint = ((rowNum - 2) / 2) + 1;
        int incSpace = 1;
        int decSpace = (rowNum - 2) - 2;
        int decStars, incStars;
        int stars = 0;
        int space = 0;

        for (int i = 0; i < rowNum; i++) {
            if (i == 0 || i == rowNum - 1) {
                for (int j = 0; j < rowNum; j++) {
                    print("*");
                }
            }
            else{
                if(i <= stopPoint){
                    decStars = (rowNum - incSpace) / 2;
                    stars = decStars;
                    space = incSpace;
                }
                else{
                    incStars = (rowNum - decSpace) / 2;
                    stars = incStars;
                    space = decSpace;
                }
                for (int j = 0; j < stars; j++) {
                    print("*");
                }
                for (int j = 0; j < space; j++) {
                    print(" ");
                }
                for (int j = 0; j < stars; j++) {
                    print("*");
                }
                if(i <= stopPoint) { incSpace += 2; }
                else { decSpace -= 2; }
            }
            println();
        }
    }
}