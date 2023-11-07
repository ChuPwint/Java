import acm.program.ConsoleProgram;

public class Exercise_2 extends ConsoleProgram {
    public void run(){
        println("This program prints both words in one line.");
        String a = readLine("Enter first word: ");
        String b = readLine("Enter second word: ");
        final int totalLength = 30;
        int wordCount = a.length() + b.length();
        int dotLineCount = totalLength - wordCount;
        print(a);
        for(int i = 0; i <= dotLineCount; i++){
            print('.');
        }
        print(b);
    }
}