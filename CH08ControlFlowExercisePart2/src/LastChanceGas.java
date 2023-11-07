import acm.program.ConsoleProgram;

public class LastChanceGas extends ConsoleProgram {
        public void run() {
                //setFont("JetBrains Mono NL-26");
                final int SENTINEL = 0;
                int tankCapacity = readInt("Tank Capacity: ");
                while (tankCapacity != SENTINEL ) {
                        int gageReading = readInt("Gage Reading: ");
                        int milesPerGallon = readInt("Miles per Gallon: ");

                        int gallonInTank = (tankCapacity * gageReading) / 100;

                        int milesCanGo = gallonInTank * milesPerGallon;

                        if (milesCanGo < 200) {
                                println("Get Gas!");
                        } else {
                                println("Safe to proceed!");
                        }
                        tankCapacity = readInt("Tank Capacity: ");
                }

                println("Finish running program!");

        }
}
