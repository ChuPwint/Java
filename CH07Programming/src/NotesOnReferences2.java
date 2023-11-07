import acm.program.ConsoleProgram;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class NotesOnReferences2 extends ConsoleProgram {
        public void run() {
                setFont("JetBrains Mono NL-26");
                String str1 = "Hello";

                String str2 = str1;
                println("str1: " + str1);
                println("str2: " + str2);

                str2 = str2 + " World";
                println("str1: " + str1);
                println("str2: " + str2);

                GregorianCalendar today = new GregorianCalendar();
                println("DAY_OF_MONTH: " + today.get(Calendar.DAY_OF_MONTH));
                GregorianCalendar tmr = today;
                tmr.add(Calendar.DAY_OF_MONTH, 1);
                println("DAY_OF_MONTH: " + today.get(Calendar.DAY_OF_MONTH));
        }
}
