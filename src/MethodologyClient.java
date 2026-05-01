import javax.swing.*;
import java.awt.*;
import java.net.*;

public class MethodologyClient {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Shop Sphere Client");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JButton showTable = new JButton("Display Table");
        JButton openWebView = new JButton("Open Web View");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(showTable);
        buttonPanel.add(openWebView);

        JEditorPane displayPane = new JEditorPane();
        displayPane.setContentType("text/html");
        displayPane.setEditable(false);

        showTable.addActionListener(e -> {
            try {
                displayPane.setPage(new URL("http://localhost:8080/images"));
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error loading page: " + ex.getMessage());
            }
        });

        openWebView.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("http://localhost:8080/images"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(displayPane), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

