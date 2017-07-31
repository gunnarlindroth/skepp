package skepp;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Gui extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter formatter8 = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    // enter coordinates on form column row
    private static final Pattern COLUMN_ROW_PATTERN = Pattern.compile("([abcdefghij])\\s*,*\\s*(\\d)");

    private final JTextField coordinateField = new JTextField(5);
    private final JTextArea historyArea = new JTextArea();

    public Gui() {
        setTitle("Skepp");

        getContentPane().add(initBasePanel());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        setSizePreferred(800, 600);

        centerScreen(this);

        setVisible(true);
    }

    private JPanel initBasePanel() {

        final JPanel p = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 1;
        gbc.gridx = 1;

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        p.add(getBoardPanel("We", true), gbc);

        gbc.gridx = 2;
        p.add(getBoardPanel("Them", false), gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.weighty = 0.0;
        gbc.gridwidth = 2;
        p.add(getActionPanel(), gbc);

        gbc.gridy = 3;
        p.add(getHistoryPanel(), gbc);

        return p;
    }

    private JPanel getBoardPanel(String title, boolean local) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        final GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        Board board = new Board();

        if (local) {
            board.generateShips();
        }
        else {
            board.generateShips();
            // TODO maybe some action is needed to initialize a "remote" (opponent) board?
            // it should probably still generate ships, but just not display them?
            // anyway the boards should probably be added as fields in this class
        }
        panel.add(new JTextArea(board.toString()), gbc);
        return panel;
    }

    private JPanel getActionPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Action"));
        final GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets.right = 4;

        gbc.gridy = 1;
        gbc.gridx = 1;

        gbc.insets.right = 5;
        panel.add(new JLabel("Coordinate:"), gbc);

        gbc.gridx = 2;

        coordinateField.setToolTipText("Enter coordinate on column+row format, e g \"a5\" or \"h3\"");
        panel.add(coordinateField, gbc);

        gbc.gridx = 3;
        gbc.weightx = 1.0;
        gbc.insets.left = 8;
        JButton fireButton = new JButton("Fire!");
        fireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFireButton();
            }
        });

        panel.add(fireButton, gbc);

        return panel;
    }

    void handleFireButton() {
        Coordinate coordinate = extractCoordinate();
        log("Firing at " + coordinate);
    }

    private void log(String string) {
        historyArea.append(getNow() + ": " + string + Util.LINE_SEPARATOR);
    }

    private String getNow() {
        return getNow(ZonedDateTime.now());
    }

    private static String getNow(ZonedDateTime zonedDateTime) {
        return formatter8.format(zonedDateTime);
    }

    private Coordinate extractCoordinate() {
        String userCoordinate = coordinateField.getText().trim();
        if (userCoordinate.isEmpty()) {
            // user relies on random luck :-)
            // TODO somehow get a random coordinate to fire at
            return new Coordinate(5, 5);
        }

        // oh, user thinks he can do better than random!
        Matcher m = COLUMN_ROW_PATTERN.matcher(userCoordinate);
        if (m.matches()) {
            String columnCharacter = m.group(1);
            int row = Integer.parseInt(m.group(2));

            // System.out.println("Column: " + columnCharacter + ", row: " + row);

            int columnIndex = getColumnIndex(columnCharacter);

            // clear field if parsing was successful
            coordinateField.setText(null);

            return new Coordinate(row, columnIndex);
        }

        return null;
    }

    private int getColumnIndex(String columnCharacter) {
        switch (columnCharacter) {
        case "a":
            return 0;
        case "b":
            return 1;
        case "c":
            return 2;
        case "d":
            return 3;
        case "e":
            return 4;
        case "f":
            return 5;
        case "g":
            return 6;
        case "h":
            return 7;
        case "j":
            return 8;
        case "k":
            return 9;
        default:
            return -1;
        }

    }

    private JPanel getHistoryPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("History"));
        final GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        historyArea.setRows(5);

        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        panel.add(scrollPane, gbc);
        return panel;
    }

    /**
     * Make sure that this frame fits the current screen size;
     *
     * @param width
     * @param height
     */
    protected void setSizePreferred(int width, int height) {
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        int w = width;
        int h = height;

        if (d.getWidth() < w) {
            w = (int) (d.getWidth() * 0.9);
        }
        if (d.getHeight() < h) {
            h = (int) (d.getHeight() * 0.9);
        }

        setSize(w, h);
    }

    /**
     * Method provided to center the window with respect to the screen.
     */
    private static void centerScreen(Window window) {
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        window.setBounds((d.width >> 1) - (window.getWidth() >> 1), (d.height >> 1) - (window.getHeight() >> 1),
                window.getWidth(), window.getHeight());
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {

            e.printStackTrace();
        }

        @SuppressWarnings("unused")
        Gui gui = new Gui();
    }
}
