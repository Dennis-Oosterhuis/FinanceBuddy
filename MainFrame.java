import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private Gebruiker gebruiker;
    private JTextField naamField, emailField, bedragField, bronField, categorieField;
    private JTextArea overzichtArea;

    public MainFrame() {
        setTitle("Financiën Overzicht");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gebruiker = new Gebruiker("Naam", "email@voorbeeld.com");

        // Panel voor gebruikersinvoer (naam, email)
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Naam:"));
        naamField = new JTextField();
        inputPanel.add(naamField);
        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        JButton saveButton = new JButton("Gegevens instellen");
        saveButton.addActionListener(e -> {
            gebruiker = new Gebruiker(naamField.getText(), emailField.getText());
        });
        inputPanel.add(saveButton);

        // Panel voor bedrag, bron, categorie
        JPanel dataPanel = new JPanel(new GridLayout(3, 2));
        dataPanel.add(new JLabel("Bedrag:"));
        bedragField = new JTextField();
        dataPanel.add(bedragField);
        dataPanel.add(new JLabel("Bron van inkomen:"));
        bronField = new JTextField();
        dataPanel.add(bronField);
        dataPanel.add(new JLabel("Categorie van uitgave:"));
        categorieField = new JTextField();
        dataPanel.add(categorieField);

        // Panel met knoppen
        JPanel buttonPanel = new JPanel();

        JButton addIncomeButton = new JButton("Inkomen toevoegen");
        addIncomeButton.addActionListener(e -> {
            try {
                double bedrag = Double.parseDouble(bedragField.getText());
                String bron = bronField.getText();
                Inkomen inkomen = new Inkomen(bedrag, bron);
                gebruiker.voegInkomenToe(inkomen);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Voer een geldig bedrag in.");
            }
        });

        JButton addExpenseButton = new JButton("Uitgave toevoegen");
        addExpenseButton.addActionListener(e -> {
            try {
                double bedrag = Double.parseDouble(bedragField.getText());
                String categorie = categorieField.getText();
                Uitgave uitgave = new Uitgave(bedrag, categorie);
                gebruiker.voegUitgaveToe(uitgave);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Voer een geldig bedrag in.");
            }
        });

        JButton showOverviewButton = new JButton("Toon Overzicht");
        showOverviewButton.addActionListener(e -> {
            overzichtArea.setText(gebruiker.toonOverzicht());
        });

        JButton saveToFileButton = new JButton("Opslaan naar Bestand");
        saveToFileButton.addActionListener(e -> {
            if (naamField.getText().isEmpty() || emailField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vul naam en e-mail in om op te slaan.");
                return;
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter("gebruikersdata.txt", true))) {
                writer.println("Naam: " + naamField.getText());
                writer.println("Email: " + emailField.getText());
                for (Inkomen i : gebruiker.getInkomens()) {
                    writer.println("Inkomen: " + i.getBedrag() + "," + i.getBron());
                }
                for (Uitgave u : gebruiker.getUitgaven()) {
                    writer.println("Uitgave: " + u.getBedrag() + "," + u.getCategorie());
                }
                writer.println("---");
                JOptionPane.showMessageDialog(null, "Gegevens opgeslagen.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Fout bij opslaan: " + ex.getMessage());
            }
        });

        JButton loadFromFileButton = new JButton("Laad Gebruiker");
        loadFromFileButton.addActionListener(e -> {
            String naam = naamField.getText();
            String email = emailField.getText();
            if (naam.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vul naam en e-mail in om te laden.");
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader("gebruikersdata.txt"))) {
                String line;
                boolean gevonden = false;
                List<Inkomen> geladenInkomens = new ArrayList<>();
                List<Uitgave> geladenUitgaven = new ArrayList<>();

                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Naam: ") && line.substring(6).equals(naam)) {
                        line = reader.readLine();
                        if (line != null && line.startsWith("Email: ") && line.substring(7).equals(email)) {
                            gevonden = true;
                            while ((line = reader.readLine()) != null && !line.equals("---")) {
                                if (line.startsWith("Inkomen: ")) {
                                    String[] parts = line.substring(9).split(",");
                                    double bedrag = Double.parseDouble(parts[0]);
                                    String bron = parts[1];
                                    geladenInkomens.add(new Inkomen(bedrag, bron));
                                } else if (line.startsWith("Uitgave: ")) {
                                    String[] parts = line.substring(9).split(",");
                                    double bedrag = Double.parseDouble(parts[0]);
                                    String cat = parts[1];
                                    geladenUitgaven.add(new Uitgave(bedrag, cat));
                                }
                            }
                            break;
                        }
                    }
                }

                if (gevonden) {
                    gebruiker = new Gebruiker(naam, email);
                    for (Inkomen i : geladenInkomens) gebruiker.voegInkomenToe(i);
                    for (Uitgave u : geladenUitgaven) gebruiker.voegUitgaveToe(u);
                    JOptionPane.showMessageDialog(null, "Gebruiker geladen!");
                } else {
                    JOptionPane.showMessageDialog(null, "Gebruiker niet gevonden of email onjuist.");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Fout bij laden: " + ex.getMessage());
            }
        });

        buttonPanel.add(addIncomeButton);
        buttonPanel.add(addExpenseButton);
        buttonPanel.add(showOverviewButton);
        buttonPanel.add(saveToFileButton);
        buttonPanel.add(loadFromFileButton);

        overzichtArea = new JTextArea(10, 40);
        overzichtArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(dataPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(new JScrollPane(overzichtArea), BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}