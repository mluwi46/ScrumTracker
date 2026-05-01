import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ScrumTracker {
    public static void main(String[] args) {
        System.out.println("Scrum Sprint: 2 Weeks");
        System.out.println("Daily Stand-up: Progress discussed");
        System.out.println("Version Control: Git");
        System.out.println("Collaboration: GitHub with Pull Requests");

        List<String> todo = new ArrayList<>();
        todo.add("Design login screen");
        todo.add("Set up database schema");

        List<String> inProgress = new ArrayList<>();
        inProgress.add("Implement authentication module");

        List<String> done = new ArrayList<>();
        done.add("Create project repository on GitHub");

        System.out.println("\n--- Scrum Board ---");
        System.out.println("To Do: " + todo);
        System.out.println("In Progress: " + inProgress);
        System.out.println("Done: " + done);

        JFrame frame = new JFrame("Scrum Tracker");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 3));

        JTextArea todoArea = new JTextArea("To Do:\n");
        todo.forEach(task -> todoArea.append("- " + task + "\n"));
        todoArea.setEditable(false);

        JTextArea inProgressArea = new JTextArea("In Progress:\n");
        inProgress.forEach(task -> inProgressArea.append("- " + task + "\n"));
        inProgressArea.setEditable(false);

        JTextArea doneArea = new JTextArea("Done:\n");
        done.forEach(task -> doneArea.append("- " + task + "\n"));
        doneArea.setEditable(false);

        frame.add(new JScrollPane(todoArea));
        frame.add(new JScrollPane(inProgressArea));
        frame.add(new JScrollPane(doneArea));

        frame.setVisible(true);
    }
}

