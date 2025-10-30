// java
import java.awt.*;
import java.awt.event.*;

public class MenuLayoutAWT extends Frame implements ActionListener {

    // Main components
    private CardLayout cardLayout;
    private Panel mainPanel; // Panel to hold the 'cards' (pages)

    // Page identifiers for CardLayout
    private final String LOGIN_PAGE = "LoginCard";
    private final String STUDENT_PAGE = "StudentCard";

    public MenuLayoutAWT() {
        setTitle("AWT MENU Practice");
        setSize(500, 350);

        // 1. Setup the Menu Bar (restored original menus)
        MenuBar menuBar = new MenuBar();

        // Create main menus
        Menu pagesMenu = new Menu("Pages");
        Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");

        // Create menu items for the 'Pages' menu
        MenuItem loginItem = new MenuItem("Login");
        MenuItem studentItem = new MenuItem("Student");

        // Add action listeners to the navigation items
        loginItem.addActionListener(this);
        studentItem.addActionListener(this);

        // Add items to the Pages menu
        pagesMenu.add(loginItem);
        pagesMenu.add(studentItem);

        // Add menus to the Menu Bar
        menuBar.add(pagesMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        // Set the Menu Bar for the Frame
        setMenuBar(menuBar);

        // 2. Setup the CardLayout Panel
        cardLayout = new CardLayout();
        mainPanel = new Panel();
        mainPanel.setLayout(cardLayout);

        // 3. Create the two pages (Panels)
        Panel loginPage = createLoginPage();
        Panel studentPage = createStudentPage();

        // Add pages to the main panel (using CardLayout)
        mainPanel.add(loginPage, LOGIN_PAGE);
        mainPanel.add(studentPage, STUDENT_PAGE);

        // 4. Add the main panel to the Frame
        add(mainPanel, BorderLayout.CENTER);

        // 5. Handle the Window Close button ('X' button)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Show the initial page (Login Page)
        cardLayout.show(mainPanel, LOGIN_PAGE);
        setVisible(true);
    }

    // --- Page Creation Methods ---

    // Creates the Login Page panel (background only on form)
    private Panel createLoginPage() {
        // Outer panel (neutral background)
        Panel outerPanel = new Panel(new GridBagLayout());
        outerPanel.setBackground(Color.lightGray);

        // Inner form panel (colored background)
        Panel formPanel = new Panel(new GridBagLayout());
        formPanel.setBackground(new Color(200, 220, 255)); // soft light blue background

        // Title Label
        Label title = new Label("LOGIN PAGE");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));

        // Input fields and button
        formPanel.add(title, getGBC(0, 0, 2, 1, GridBagConstraints.CENTER));
        formPanel.add(new Label("Username"), getGBC(0, 1, 1, 1, GridBagConstraints.EAST));
        formPanel.add(new TextField(20), getGBC(1, 1, 1, 1, GridBagConstraints.WEST));
        formPanel.add(new Label("Password"), getGBC(0, 2, 1, 1, GridBagConstraints.EAST));
        formPanel.add(new TextField(20), getGBC(1, 2, 1, 1, GridBagConstraints.WEST));

        Button loginBtn = new Button("LOGIN");
        loginBtn.setBackground(new Color(0, 102, 204));
        loginBtn.setForeground(Color.white);
        formPanel.add(loginBtn, getGBC(1, 3, 1, 1, GridBagConstraints.CENTER));

        // Add form panel inside outer panel (centered)
        outerPanel.add(formPanel, getGBC(0, 0, 1, 1, GridBagConstraints.CENTER));

        return outerPanel;
    }

    // Creates the Student Page panel
    private Panel createStudentPage() {
        Panel p = new Panel(new GridBagLayout());
        p.setBackground(new Color(220, 220, 255)); // Light blue color

        Label studentInfo = new Label("Student Page: [Mukarubibi Rehema] - [24RP01069]");
        studentInfo.setFont(new Font("Serif", Font.BOLD, 16));

        p.add(studentInfo, getGBC(0, 0, 1, 1, GridBagConstraints.CENTER));
        return p;
    }

    // Helper for GridBagLayout to keep code cleaner
    private GridBagConstraints getGBC(int x, int y, int w, int h, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = anchor;
        return gbc;
    }

    // Menu Item Action Listener
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Login")) {
            cardLayout.show(mainPanel, LOGIN_PAGE);
        } else if (command.equals("Student")) {
            cardLayout.show(mainPanel, STUDENT_PAGE);
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        new MenuLayoutAWT();
    }
}
