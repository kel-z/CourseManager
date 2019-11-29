package ui;

import exceptions.InvOptionException;
import exceptions.InvPersonException;
import exceptions.InvSubjectException;
import exceptions.MaxCapacityException;
import javafx.scene.layout.BorderPaneBuilder;
import model.Institution;
import model.InstitutionMonitor;
import model.Subject;
import network.WebMessage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observer;
import java.util.Scanner;

public class InstitutionRun extends JFrame {
    Scanner scanner;
    String input;
    Institution inst;
    Boolean flag;
    private JTextArea inputGUI;
    private JLabel status;
    private JButton advanceButton;

    private JPanel leftPanel;

    public InstitutionRun(InstitutionMonitor observer) {
        super("Institution");
        setMinimumSize(new Dimension(500, 100));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        "Institution"),
                BorderFactory.createEmptyBorder(10,10,10,10)));

        status = new JLabel("Institution name:");
        status.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(status);

        inputGUI = new JTextArea();
        leftPanel.add(inputGUI);

        advanceButton = new JButton("Enter");
        advanceButton.setAlignmentX(SwingConstants.CENTER);
        advanceButton.setActionCommand("name");
        leftPanel.add(advanceButton);

        leftPanel.add(Box.createRigidArea(new Dimension(0,10)));
        add(leftPanel);

        advanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("name")) {
                    inst = new Institution(inputGUI.getText(), observer);
                    setTitle(inputGUI.getText());
                    status.setText("Do something:");
                    inputGUI.setText("");
                    advanceButton.setActionCommand("input");
                    try {
                        inst.load("data.txt");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (MaxCapacityException ex) {
                        ex.printStackTrace();
                    }
                } else if (e.getActionCommand().equals("input")) {
                    guiRun1(inputGUI.getText().toLowerCase());
                } else if (e.getActionCommand().equals("add")) {
                    guiAdd();
                }
            }
        });

        pack();
        setVisible(true);
        input = "";
        scanner = new Scanner(System.in);
        System.out.println("Institution name:");
        inst = new Institution(scanner.nextLine(), observer);
        flag = true;
    }

    public void guiAdd() {
        JFrame window = new JFrame();
        window.setMinimumSize(new Dimension(200, 100));
        window.setLocationRelativeTo(null);
        JTextArea input1 = new JTextArea(0, 30);
        JTextArea input2 = new JTextArea();
        JTextArea input3 = new JTextArea();
        window.add(new JLabel("First name:"), BorderLayout.PAGE_START);
        window.add(input1,BorderLayout.PAGE_END);
        window.add(new JLabel("Last name:"), BorderLayout.PAGE_START);
        window.add(input2, BorderLayout.PAGE_END);
        //window.add(new JLabel("Subject:"), BorderLayout.AFTER_LAST_LINE);
        //window.add(input3, BorderLayout.AFTER_LAST_LINE);
        window.pack();
        window.setVisible(true);
    }

    public void guiRun1(String str) {
        if (str.equals("stop")) {
            displayMsg("Stopping!");
            try {
                inst.save("data.txt");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (str.equals("add")) {
            inputGUI.setText("");
            status.setText("Add What?");
            advanceButton.setActionCommand("add");
        }
    }

//    // EFFECTS: reads user input to perform tasks
//    public void run(Institution i) throws IOException, InvPersonException, InvOptionException,
//            MaxCapacityException, InvSubjectException {
//        System.out.println("\nDo something:");
//        input = scanner.nextLine().toLowerCase();
//        if (input.equals("stop")) {
//            i.save("data.txt");
//            flag = false;
//        } else if (input.equals("remove")) {
//            remove();
//        } else if (input.equals("info")) {
//            info();
//        } else if (input.equals("fire")) {
//            i.fire();
//        } else if (input.equals("add")) {
//            add();
//        } else {
//            throw new InvOptionException();
//        }
//    }

    private void displayMsg(String str) {
        JFrame window = new JFrame("Message");
        JLabel msg = new JLabel(str);
        window.setMinimumSize(new Dimension(200, 100));
        window.add(msg, BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        if (str.equals("Stopping!")) {
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
        inputGUI.setText("");
    }

    // MODIFIES: this
    // EFFECTS: takes user input to add a professor
    public void addProf() throws MaxCapacityException {
        System.out.println("\nFirst name: ");
        String firstName = scanner.nextLine();
        System.out.println("\nLast name: ");
        String lastName = scanner.nextLine();
        System.out.println("\nSubject: ");
        String input = scanner.nextLine();
        Subject subject = inst.subjectGet(input);
        inst.addProf(firstName, lastName, subject);
    }

    // MODIFIES: this
    // EFFECTS: takes user input to add a student
    public void addStud() throws MaxCapacityException {
        System.out.println("\nFirst name: ");
        String firstName = scanner.nextLine();
        System.out.println("\nLast name: ");
        String lastName = scanner.nextLine();
        System.out.println("\ngpa: ");
        double gpa = scanner.nextDouble();
        scanner.nextLine();
        inst.addStudent(firstName, lastName, gpa);
    }

    // MODIFIES: inst
    // EFFECTS: takes user input to specify person to add to population
    public boolean add() throws InvPersonException, MaxCapacityException {
        System.out.println("\nAdd what?");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("student")) {
            addStud();
        } else if (input.equals("professor")) {
            addProf();
        } else {
            throw new InvPersonException();
        }
        return true;
    }

    public void remove() throws InvPersonException, InvOptionException {
        System.out.println("\nRemove what?");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("professor")) {
            System.out.println("\nRemove who? (first name)");
            input = scanner.nextLine().toLowerCase();
            inst.removeProf(input);
        } else {
            throw new InvOptionException();
        }
    }

    // MODIFIES: inst
    // EFFECTS: takes user input to get info
    public void info() throws InvSubjectException, InvOptionException {
        System.out.println("\nGet what?");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("info")) {
            System.out.println(inst);
        } else if (input.equals("subject")) {
            System.out.println("\nWhich subject?");
            input = scanner.nextLine();
            inst.getSubjectList(input);
        } else {
            throw new InvOptionException();
        }
    }

    // EFFECTS: reads user input to perform tasks
    public void run(Institution i, String in) throws IOException, InvPersonException, InvOptionException,
            MaxCapacityException, InvSubjectException {
        System.out.println("\nDo something:");
        input = in.toLowerCase();
        if (input.equals("stop")) {
            i.save("data.txt");
            flag = false;
        } else if (input.equals("remove")) {
            remove();
        } else if (input.equals("info")) {
            info();
        } else if (input.equals("fire")) {
            i.fire();
        } else if (input.equals("add")) {
            add();
        } else {
            throw new InvOptionException();
        }
    }

    public static void main(String[] args) throws IOException, MaxCapacityException {
        WebMessage web = new WebMessage();
        web.welcome();
        InstitutionMonitor observer = new InstitutionMonitor();
        InstitutionRun ins = new InstitutionRun(observer);
//        ins.inst.load("data.txt");
//        while (ins.flag) {
//            try {
//                ins.run(ins.inst);
//            } catch (MaxCapacityException e) {
//                System.out.println("Capacity maxed!");
//            } catch (InvSubjectException e) {
//                System.out.println("No professors for subject!");
//            } catch (Exception e) {
//                System.out.println("Invalid option!");
//            }
//        }
        ins.inst.printPopulation();
        observer.printStats();
    }
}
