import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LabelCPanel extends CreatorPanel {

    private JTextField labelNameField;
    private String labelName;
    public LabelCPanel(FormListener formListener, PrintListener printListener)
    {
        super("Label creator", formListener, printListener);
        labelName = null;

        makeButtons();
        addHelpButton(0,10,7,"Label creator:\n\n" +
                "SET TEXT : \n" +
                "geeft de syntax weer om text in te stellen, De gewenste text zal zelf nog moeten worden ingetyped." +
                "\n\n" +
                "SET PICTURE :\n" +
                "methode om de picture in te stellen. de parameter is al deels ingevuld door de \n" +
                "methode dat Ronny ook gebruikte tijdens de les. \n\n" +
                "CREATE ICON METHOD : \n" +
                "Geeft de methode mee voor een icon te maken van een path. Deze methode is nodig\n" +
                "om een icoon toe te voegen aan het label.\n\n");
        addInfoLabel(20);
    }

    private void makeButtons()
    {

        labelNameField = new JTextField(10);
        JLabel nameLabel = new JLabel("label name:");
        labelNameField.setMinimumSize(new Dimension(95,20));
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
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(labelNameField, gc);
        /*
        Second row
         */


        gc.gridy++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,5);
        add(new JButton(new AbstractAction("Get name") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                labelName = labelNameField.getText();
                addTextToInfoLabel("label name set: " + labelName);
            }
        }), gc);

        gc.gridx = 0;
        gc.gridy++;
        gc.insets = new Insets(0,0,0,0);
        add(new JButton(new AbstractAction("Set text") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                printUsingLabelName(true, labelName +".setText(*textHier*);\n");
            }
        }), gc);

        gc.gridy++;
        add(new JButton(new AbstractAction("set picture") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                printUsingLabelName(true,labelName + ".setIcon(iconLoad(*String hier*));\n");
            }
        }),gc);

        gc.gridy++;
        gc.gridwidth = 2;
        add(new JButton(new AbstractAction("Create icon method") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
             printText(true,"private ImageIcon iconLoad(String path)\n" +
                     "    {\n" +
                     "        ImageIcon icon = new ImageIcon(path);\n" +
                     "        return icon;\n" +
                     "    }\n");
            }
        }), gc);

;    }
    private void printUsingLabelName(boolean append, String text)
    {
        if (labelName == null ||labelName.equals(""))
        {
            addTextToInfoLabel("stel label name in aub.");
        }
        else
            printText(append, text);
    }
}
