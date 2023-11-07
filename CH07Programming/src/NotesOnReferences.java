import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

import java.awt.Color;

public class NotesOnReferences extends GraphicsProgram {
        public void run() {
                GLabel hw = new GLabel("Hello World!", 200, 100);

                GLabel lbl = hw;
                lbl.setColor(Color.RED);
                lbl.setFont("Impact-Italic-36");

                add(hw);
        }
}
