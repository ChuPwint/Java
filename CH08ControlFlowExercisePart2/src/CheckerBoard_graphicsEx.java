import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

public class CheckerBoard_graphicsEx extends GraphicsProgram {
    public void run(){
        final int BOARD_SIZE = 8;
        double size = (double)(getHeight() / BOARD_SIZE);
        for(int j = 0; j < BOARD_SIZE; j++){
            double y = j * size;
            for(int i = 0; i < BOARD_SIZE; i++){
                double x = i * size;
                GRect block = new GRect(x, y, size, size);
                boolean isFilled = ((i + j) % 2 == 1);
                block.setColor(Color.GRAY);
                block.setFilled(isFilled);
                add(block);
            }
        }
    }
}
