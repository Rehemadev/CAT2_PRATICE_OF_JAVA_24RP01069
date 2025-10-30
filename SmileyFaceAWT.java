import java.awt.*;
import java.awt.event.*;

// 1. Custom Canvas class for drawing the face
class FaceCanvas extends Canvas {
    public FaceCanvas() {
        setBackground(Color.white); // Set the background color
        setSize(400, 400); // Set a preferred size
    }

    // Override the paint method to draw the shapes
    @Override
    public void paint(Graphics g) {
        // Define coordinates (adjust these based on the Canvas size for centering)
        int x = 50; // Top-left X coordinate for the main circle
        int y = 50; // Top-left Y coordinate for the main circle
        int diameter = 300; // Diameter of the main circle

        // --- Draw the Main Head (Outer Circle) ---
        // Set color for the outline (e.g., orange/brown)
        g.setColor(new Color(205, 133, 63)); // Using a Siennna-like color for the outline

        // Draw the outline circle
        g.drawOval(x, y, diameter, diameter);

        // --- Draw the Eyes (Two small filled circles) ---
        // Set color for the eyes (e.g., dark brown)
        g.setColor(new Color(139, 69, 19));

        int eyeSize = 25;
        // Left Eye (relative to the main circle's x, y)
        g.fillOval(x + 100, y + 100, eyeSize, eyeSize);

        // Right Eye
        g.fillOval(x + diameter - 100 - eyeSize, y + 100, eyeSize, eyeSize);

        // --- Draw the Mouth (Arc) ---
        // Use the same color as the outline for the mouth line
        g.setColor(new Color(255, 140, 0)); // Darker orange for the mouth

        // drawArc(x, y, width, height, startAngle, arcAngle)
        // Draw an arc that represents a smile
        int mouthWidth = diameter / 2;
        int mouthHeight = diameter / 3;
        int mouthX = x + (diameter - mouthWidth) / 2;
        int mouthY = y + diameter - mouthHeight - 50;

        // The arc starts at 0 degrees (right) and sweeps 180 degrees (clockwise)
        // to form a downward arc. To make it a smile, we need a negative start
        // angle or adjust the arc. Common angles for smile: 30 to 120 degrees sweep, starting at 210
        // Drawing a simpler arc:
        g.drawArc(mouthX, mouthY, mouthWidth, mouthHeight, 210, 120);
    }
}

// 2. Main Frame class
public class SmileyFaceAWT extends Frame {
    public SmileyFaceAWT() {
        // Set frame properties
        setTitle("My shapes");
        setSize(450, 450); // Set window size larger than the canvas

        // Add the drawing canvas to the frame
        add(new FaceCanvas());

        // 3. Handle the Window Close button ('X' button)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0); // Terminate the application when the close button is clicked
            }
        });

        setVisible(true); // Make the frame visible
    }

    // 4. Main method to run the program
    public static void main(String[] args) {
        new SmileyFaceAWT();
    }
}