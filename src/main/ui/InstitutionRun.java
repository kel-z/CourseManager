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
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import static java.lang.Double.parseDouble;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    private JPanel start;

    private JFrame main;

    public InstitutionRun(InstitutionMonitor observer) {
        super("Institution");
        setMinimumSize(new Dimension(500, 100));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        start = new JPanel();
        start.setLayout(new BoxLayout(start, BoxLayout.PAGE_AXIS));
        start.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        "Institution"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        status = new JLabel("Institution name:");
        status.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.add(status);
        inputGUI = new JTextArea();
        start.add(inputGUI);
        advanceButton = new JButton("Enter");
        advanceButton.setAlignmentX(SwingConstants.CENTER);
        advanceButton.setActionCommand("name");
        start.add(advanceButton);

        start.add(Box.createRigidArea(new Dimension(0,10)));
        add(start);

        main = new JFrame("Institution");
        main.setMinimumSize(new Dimension(400, 150));
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null);
        main.getContentPane().setLayout(new BoxLayout(main.getContentPane(), BoxLayout.Y_AXIS));

        JButton info = new JButton("Info");
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMsg(inst.printPopulation());
                playSound();
            }
        });
        main.add(info);
        JButton addS = new JButton("Add Student");
        addS.setAlignmentX(Component.CENTER_ALIGNMENT);
        addS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiAddS();
                playSound();
            }
        });
        main.add(addS);
        JButton addP = new JButton("Add Professor");
        addP.setAlignmentX(Component.CENTER_ALIGNMENT);
        addP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiAddP();
                playSound();
            }
        });
        main.add(addP);
        JButton removeP = new JButton("Remove Professor");
        removeP.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiRemoveP();
                playSound();
            }
        });
        main.add(removeP);
        JButton save = new JButton("Save");
        save.setAlignmentX(Component.CENTER_ALIGNMENT);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    inst.save("data.txt");
                    displayMsg("Saved!");
                    playSound();
                } catch (IOException ex) {
                    displayMsg("Error!");
                }
            }
        });
        main.add(save);


        advanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("name") && !inputGUI.getText().equals("")) {
                    main.setTitle("Institution: " + inputGUI.getText());
                    inst = new Institution(inputGUI.getText(), observer);
                    try {
                        inst.load("data.txt");
                    } catch (IOException ex) {
                        displayMsg("Error!");
                    } catch (MaxCapacityException ex) {
                        displayMsg("Max Capacity Error!");
                    }
                    playSound();
                    setVisible(false);
                    main.pack();
                    main.setVisible(true);
                }
            }
        });


        pack();
        setVisible(true);
//        input = "";
//        scanner = new Scanner(System.in);
//        System.out.println("Institution name:");
//        inst = new Institution(scanner.nextLine(), observer);
//        flag = true;
    }

    // EFFECTS: plays sound
    public void playSound() {
        InputStream music;
        File pop = new File(".\\src\\main\\sound\\hmm_01.wav");
        try {
            music = new FileInputStream(pop);
            AudioStream audio = new AudioStream(music);
            AudioPlayer.player.start(audio);
        } catch (Exception e) {
            displayMsg("Sound Error!");
            System.out.println(e);
        }

    }

    // MODIFIES: removeP
    // EFFECTS: initiates the GUI fields
    public void initiateFieldsRP(JFrame removeP, JTextField input1) {
        removeP.setLocationRelativeTo(null);
        removeP.getContentPane().setLayout(new BoxLayout(removeP.getContentPane(), BoxLayout.Y_AXIS));
        removeP.add(new JLabel("First Name:"));
        removeP.add(input1);
    }

    // EFFECTS: display frame for remove professor
    public void guiRemoveP() {
        JFrame removeP = new JFrame("Remove Professor");
        JTextField input1 = new JTextField();
        //main.setMinimumSize(new Dimension(200, 200));
        initiateFieldsRP(removeP, input1);
        JButton enter = new JButton("Remove");
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    displayMsg(inst.removeProf(input1.getText()));
                    playSound();
                } catch (InvPersonException ex) {
                    displayMsg("No person exists!");
                }
                removeP.setVisible(false);
            }
        });
        removeP.add(enter);
        removeP.pack();
        removeP.setVisible(true);

    }

    // MODIFIES: frame
    // EFFECTS: initiates fields for add student GUI
    public void initiateFieldsS(JFrame frame) {
        frame.add(new JLabel("First Name:"));
        JTextField input1 = new JTextField();
        frame.add(input1);
        frame.add(new JLabel("Last Name:"));
        JTextField input2 = new JTextField();
        frame.add(input2);
        frame.add(new JLabel("GPA:"));
        JTextField input3 = new JTextField();
        frame.add(input3);
        initiateEnterS(frame, input1, input2, input3);
    }

    // MODIFIES: frame
    // EFFECTS: initiates the enter button for add student GUI
    public void initiateEnterS(JFrame frame, JTextField in1, JTextField in2, JTextField in3) {
        JButton enter = new JButton("Add");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    inst.addStudent(in1.getText(), in2.getText(), parseDouble(in3.getText()));
                    playSound();
                } catch (Exception ex) {
                    displayMsg("Error!");
                }
                frame.setVisible(false);
            }
        });
        frame.add(enter);
    }

    // EFFECTS: display frame for add student
    public void guiAddS() {
        JFrame addS = new JFrame("Add Student");
        //main.setMinimumSize(new Dimension(200, 200));
        addS.setLocationRelativeTo(null);
        addS.getContentPane().setLayout(new BoxLayout(addS.getContentPane(), BoxLayout.Y_AXIS));
        initiateFieldsS(addS);
        addS.pack();
        addS.setVisible(true);
    }

    // EFFECTS: display frame for add student
    public void guiAddP() {
        JFrame addP = new JFrame("Add Professor");
        //main.setMinimumSize(new Dimension(200, 200));
        addP.setLocationRelativeTo(null);
        addP.getContentPane().setLayout(new BoxLayout(addP.getContentPane(), BoxLayout.Y_AXIS));
        initiateFieldsP(addP);
        addP.pack();
        addP.setVisible(true);
    }

    // MODIFIES: frame
    // EFFECTS: initiates fields for add professor GUI
    public void initiateFieldsP(JFrame frame) {
        frame.add(new JLabel("First Name:"));
        JTextField input1 = new JTextField();
        frame.add(input1);
        frame.add(new JLabel("Last Name:"));
        JTextField input2 = new JTextField();
        frame.add(input2);
        frame.add(new JLabel("Subject:"));
        JTextField input3 = new JTextField();
        frame.add(input3);
        initiateEnterP(frame, input1, input2, input3);
    }

    // MODIFIES: frame
    // EFFECTS: initiates and adds the enter button for add professor GUI
    public void initiateEnterP(JFrame frame, JTextField in1, JTextField in2, JTextField in3) {
        JButton enter = new JButton("Add");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    inst.addProf(in1.getText(), in2.getText(), inst.subjectGet(in3.getText()));
                    playSound();
                } catch (Exception ex) {
                    displayMsg("Error!");
                }
                frame.setVisible(false);
            }
        });
        frame.add(enter);
    }

    // EFFECTS: creates a message window containing the message str
    private void displayMsg(String str) {
        JFrame window = new JFrame("Message");
        window.setLayout(new BorderLayout(5, 5));

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

    // EFFECTS: reads user input to perform tasks
    public void run(Institution i) throws IOException, InvPersonException, InvOptionException,
            MaxCapacityException, InvSubjectException {
        System.out.println("\nDo something:");
        input = scanner.nextLine().toLowerCase();
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
//        ins.inst.printPopulation();
//        observer.printStats();
    }
}
