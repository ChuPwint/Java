/*
 * File: CentimetersToFeetAndInches.java
 * -------------------------------------
 * This program converts centimeters to an integral number of feet
 * and any remaining inches. */

import acm.program.ConsoleProgram;

public class CentimetersToFeetAndInches extends ConsoleProgram {
        public void run() {
                setFont("JetBrains Mono NL-26");
                println("This program converts centimeters to feet and inches.");

                final int INCHES_PER_FOOT = 12;
                final double CENTIMETERS_PER_INCH = 2.54;
                double cm = readDouble("Enter value in centimeters: ");
                double totalInches = cm / CENTIMETERS_PER_INCH;
                int feet = (int) (totalInches / INCHES_PER_FOOT);
                double inches = totalInches - INCHES_PER_FOOT * feet;
                println(cm + "cm=" + feet + "ft+" + inches + "in");
        }

}