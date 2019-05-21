import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
public class TextFieldCPanel extends CreatorPanel {

    private JTextField nameField;
    private String textFieldName;
    private JTextField heightField, widthField;

    public TextFieldCPanel( FormListener formListener, PrintListener printListener)
    {
        super("TextField creator", formListener, printListener);
        textFieldName = null;
        heightField = new JTextField();
        widthField = new JTextField();

        makeButtons();
        addHelpButton(0,19,7, "Text field creator: \n\n" +
                "NEW TEXTFIELD : \n" +
                "Syntax voor een nieuw textField. Je kan in de JTextField(*hier*)\n" +
                "nog een zin meegeven of een getal 5 = plaats voor 5 letters\n\n" +
                "SET PREFERED SIZE : \n" +
                "zets de prefered size (word soms genegeerd door de layout\n\n" +
                "RETRIEVE TEXT: \n" +
                "Deze methode geeft een string terug als je wil weten wat de gebruiker heeft ingegeven\n" +
                "roep je deze methode op.\n" +
                "\n\n Beetje info van java docs : \n" +
                "JTextField is a lightweight component that allows the editing of a single line of text. \n" +
                "For information on and examples of using text fields, see How to Use Text Fields in The Java Tutorial.\n" +
                "how to use textfields link :\n" +
                "https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html");
        addInfoLabel(20);
    }

    private void makeButtons()
    {

        nameField = new JTextField(10);
        JLabel nameLabel = new JLabel("textField name:");
        nameField.setMinimumSize(new Dimension(95,20));
        GridBagConstraints gc = new GridBagConstraints();

        gc.weighty = 0.1;
        gc.weightx = 0.1;

        /*
        First row
         */
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,10);
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(nameField, gc);
        /*
        Second row
         */
        gc.weightx = 0.1;
        gc.weighty = 0.2;
        gc.gridx = 1;
        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,5);
        add(new JButton(new AbstractAction("Get name") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textFieldName = nameField.getText();
                addTextToInfoLabel("Button name set: " + textFieldName);
            }
        }), gc);

        /*
        third row
         */
        gc.gridx = 0;
        gc.gridy++;
        add(new JButton(new AbstractAction("new TextField") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                printUsingTextFieldName(true, "JTextField " + textFieldName
                + " = new JTextField(); \n");
            }
        }), gc );

        /*
         4th row
         */
        gc.gridx = 0;
        gc.gridy++;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("Panel Height:"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Panel Width:"), gc);

        /*
        5th row
         */
        gc.gridx = 0;
        gc.gridy++;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_START;
        add(heightField, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(widthField, gc);

        /*
        6th row
         */
        gc.gridx = 0;
        gc.gridy++;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(new JButton(new AbstractAction("Set preferred size") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    int height = new Integer(heightField.getText());
                    int width = new Integer(widthField.getText());
                    printUsingTextFieldName(true, textFieldName + ".setPreferredSize(new Dimension("+width + "," + height + "));\n");

                } catch (NumberFormatException n)
                {
                    addTextToInfoLabel("Panel height or width can only accept numbers !! It also cant be empty, try again");
                }
            }
        }), gc);

        /*
        7th row
         */
        gc.gridx = 0;
        gc.gridy++;

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(new JButton(new AbstractAction("retrieve text") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                printUsingTextFieldName(true, textFieldName + ".getText(); // dit returned de text in het veld als een String.\n");
            }
        }), gc);

    }

    private void printUsingTextFieldName(boolean append, String text)
    {
        if (textFieldName == null ||textFieldName.equals(""))
        {
            addTextToInfoLabel("stel textField name in aub.");
        }
        else
            printText(append, text);
    }
}
