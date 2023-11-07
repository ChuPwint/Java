import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.ConsoleProgram;
import acm.program.GraphicsProgram;
import java.awt.Color;

public class Ex_7 extends GraphicsProgram {
    public void run() {
        final int BOARD_SIZE = 8;
        final int MARGIN = 5;
        double squareSize = (double) (getHeight() / BOARD_SIZE);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                double x = j * squareSize;
                double center = (getWidth() - (BOARD_SIZE * squareSize)) / 2.0;
                double y = i * squareSize;

                GRect blocks = new GRect(center + x, y, squareSize, squareSize);
                blocks.setColor(Color.GRAY);
                boolean isFilled = (i + j) % 2 == 1;
                blocks.setFilled(isFilled);
                add(blocks);
                
                double xCirclePlaces = center + x + MARGIN;
                double yCirclePlaces = y + MARGIN;

                GOval oval = new GOval(xCirclePlaces, yCirclePlaces, squareSize - 2 * MARGIN, squareSize - 2 * MARGIN);
                if(isFilled && (i <= 2 || i >= 5)){
                    if(i >= 5) { oval.setColor(Color.black); }
                    else{ oval.setColor(Color.RED); }
                    oval.setFilled(true);
                    add(oval);
                }
            }
        }
    }
}