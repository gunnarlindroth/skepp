package skepp;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Gui extends JFrame {

    private static final long serialVersionUID = 1L;

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
        p.add(getBoardPanel("We"), gbc);

        gbc.gridx = 2;
        p.add(getBoardPanel("Them"), gbc);

        return p;
    }

    private JPanel getBoardPanel(String title) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        
        
        Board board = new Board();
        board.generateShips();
        panel.add(new JTextArea(board.toString()), gbc);
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
        
        Gui gui = new Gui();
    }
}
