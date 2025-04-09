import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private Gebruiker gebruiker;
    private JTextField naamField, emailField, bedragField, bronField, categorieField;
    private JTextArea overzichtArea;

    public MainFrame() {
        setTitle("Financiën Overzicht");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gebruiker = new Gebruiker("Naam", "email@voorbeeld.com");

        // Panel voor gebruikersinvoer (naam, email)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Naam:"));
        naamField = new JTextField();
        inputPanel.add(naamField);
        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        // Knop om gegevens op te slaan
        JButton saveButton = new JButton("Opslaan");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gebruiker = new Gebruiker(naamField.getText(), emailField.getText());
            }
        });

        inputPanel.add(saveButton);

        // Panel voor inkomens- en uitgaveninvoer
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(3, 2));
        dataPanel.add(new JLabel("Bedrag:"));
        bedragField = new JTextField();
        dataPanel.add(bedragField);
        dataPanel.add(new JLabel("Bron van inkomen:"));
        bronField = new JTextField();
        dataPanel.add(bronField);
        dataPanel.add(new JLabel("Categorie van uitgave:"));
        categorieField = new JTextField();
        dataPanel.add(categorieField);

        // Knoppen om inkomen en uitgave toe te voegen
        JButton addIncomeButton = new JButton("Inkomen toevoegen");
        addIncomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double bedrag = Double.parseDouble(bedragField.getText());
                    String bron = bronField.getText();
                    Inkomen inkomen = new Inkomen(bedrag, bron);
                    gebruiker.voegInkomenToe(inkomen);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Voer een geldig bedrag in.");
                }
            }
        });

        JButton addExpenseButton = new JButton("Uitgave toevoegen");
        addExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double bedrag = Double.parseDouble(bedragField.getText());
                    String categorie = categorieField.getText();
                    Uitgave uitgave = new Uitgave(bedrag, categorie);
                    gebruiker.voegUitgaveToe(uitgave);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Voer een geldig bedrag in.");
                }
            }
        });

        // Paneel voor overzicht en knoppen
        JPanel buttonPanel = new JPanel();
        JButton showOverviewButton = new JButton("Toon Overzicht");
        showOverviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                overzichtArea.setText("");  // Reset het overzicht
                overzichtArea.append(gebruiker.toonOverzicht());  // Toon het overzicht in de JTextArea
            }
        });

        buttonPanel.add(addIncomeButton);
        buttonPanel.add(addExpenseButton);
        buttonPanel.add(showOverviewButton);

        // Textarea voor overzicht
        overzichtArea = new JTextArea(10, 40);
        overzichtArea.setEditable(false);

        // Voeg alle panels toe aan het hoofdframe
        add(inputPanel, BorderLayout.NORTH);
        add(dataPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(new JScrollPane(overzichtArea), BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}
