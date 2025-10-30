import java.awt.*;
import java.awt.event.*;

public class MultiplicationTableAWT extends Frame implements ActionListener {

    // Components required for the interface
    private TextField inputField;
    private TextArea outputArea;
    private Button displayButton;

    public MultiplicationTableAWT() {
        // 1. Setup the Frame
        setTitle("AWT Practice");
        setSize(450, 400);
        // Use BorderLayout for the main frame to give some padding (insets)
        setLayout(new BorderLayout(10, 10)); // Added padding for better appearance

        // 2. Create Panels for Layout Organization

        // Panel for Input Field (top-left) - this will be white or default background
        Panel inputFieldPanel = new Panel(new FlowLayout(FlowLayout.CENTER)); // Use FlowLayout for simple centering

        // Panel for Display Button (middle-left)
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));

        // Panel for the result (Right side) - THIS PANEL WILL HAVE THE ORANGE BACKGROUND
        Panel resultPanel = new Panel(new BorderLayout());
        resultPanel.setBackground(new Color(255, 140, 0)); // ORANGE color for the output area background (Approximation)

        // 3. Initialize and add Input Components
        inputField = new TextField("5", 5); // Default value '5' as shown in image
        inputField.setFont(new Font("Monospaced", Font.BOLD, 18));
        // Removed call to inputField.setAlignment(...) because it's not available in this environment.
        // The FlowLayout on inputFieldPanel centers the field itself.
        inputFieldPanel.add(inputField); // Add to its dedicated panel

        displayButton = new Button("DISPLAY");
        displayButton.setBackground(new Color(0, 102, 204)); // Blue color for the button
        displayButton.setForeground(Color.white); // White text on button
        displayButton.addActionListener(this);
        buttonPanel.add(displayButton); // Add to its dedicated panel

        // 4. Create a left-side panel to stack inputFieldPanel and buttonPanel vertically
        Panel leftSidePanel = new Panel();
        leftSidePanel.setLayout(new GridBagLayout()); // Using GridBagLayout for flexible vertical stacking
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center horizontally
        leftSidePanel.add(inputFieldPanel, gbc);

        gbc.gridy = 1;
        leftSidePanel.add(buttonPanel, gbc);

        // Add some filler to push components to the top if needed
        gbc.gridy = 2;
        gbc.weighty = 1.0; // Make this row expand vertically
        leftSidePanel.add(new Label(""), gbc); // Empty label as a filler


        // 5. Initialize and add Output Component
        outputArea = new TextArea(10, 20);
        outputArea.setEditable(false); // User cannot edit the results
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        outputArea.setText(generateTable(5)); // Display default table on start
        // Set TextArea background to match the parent panel's background visually
        // AWT TextArea does not have a transparent background property like Swing components.
        // We set its background to the same orange as the resultPanel.
        outputArea.setBackground(new Color(255, 140, 0)); // Match parent panel color
        outputArea.setForeground(Color.BLACK); // Ensure text is visible

        // Add output area to resultPanel, potentially with some internal padding
        resultPanel.add(outputArea, BorderLayout.CENTER);

        // 6. Create a main content panel to arrange leftSidePanel and resultPanel side-by-side
        Panel contentPanel = new Panel(new GridLayout(1, 2, 10, 0)); // 1 row, 2 columns, 10px horizontal gap
        contentPanel.add(leftSidePanel);
        contentPanel.add(resultPanel);

        // Add the main content panel to the frame's center
        add(contentPanel, BorderLayout.CENTER);

        // 7. Handle Window Closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    /**
     * Event handler for the "DISPLAY" button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == displayButton) {
            try {
                // Read the number from the text field
                int number = Integer.parseInt(inputField.getText());

                // Generate and set the multiplication table text
                outputArea.setText(generateTable(number));

            } catch (NumberFormatException ex) {
                // Handle non-numeric input error
                outputArea.setText("Error: Please enter a valid number.");
            }
        }
    }

    /**
     * Generates the multiplication table string for a given number.
     * @param number The base number for the table.
     * @return The formatted table string.
     */
    private String generateTable(int number) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            // Format: 5 * 1 = 5
            sb.append(number).append(" * ").append(i).append(" = ").append(number * i).append("\n");
        }
        return sb.toString();
    }

    // Main method
    public static void main(String[] args) {
        // Ensure GUI updates are handled on the Event Dispatch Thread
        EventQueue.invokeLater(MultiplicationTableAWT::new);
    }
}
