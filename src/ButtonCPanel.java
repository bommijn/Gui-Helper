import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonCPanel extends CreatorPanel{
    private String buttonName;
    private JTextField nameField;

    public ButtonCPanel(FormListener formListener, PrintListener printListener)
    {
        super("Button creator", formListener, printListener);
        buttonName = null;

        makeButtons();
        addHelpButton(0,19,8,"Button creator:\n\n" +
                "NEW BUTTON : \n" +
                "Geeft de syntax voor een nieuwe knop\n\n" +
                "ADD ACTIONLISTENER : \n" +
                "voegt een actionlistener toe aan de knop (methode voor als je erop clickt)\n\n" +
                "ADD TO LAYOUT :\n" +
                "Voegt de knop toe aan de layout, naam van paneel/con/frame/whatever, moet je zelf nog ingeven\n\n" +
                "");
        addInfoLabel(20);
    }


    private void makeButtons()
    {
        nameField = new JTextField(10);
        JLabel nameLabel = new JLabel("button name:");
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

        gc.weightx = 0.1;
        gc.weighty = 0.2;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,5);
        add(new JButton(new AbstractAction("Get name") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                buttonName = nameField.getText();
                addTextToInfoLabel("Button name set: " + buttonName);
            }
        }), gc);

        /*
        line 3
         */
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(new JButton(new AbstractAction("new Button") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                printUsingButonName(true, "JButton " + buttonName + " = new JButton(*button title here*);\n");
            }
        }), gc);

         /*
        line 4
         */
        gc.gridx = 0;
        gc.gridy++;
        gc.gridwidth = 2;
        add(new JButton(new AbstractAction("Add actionlistener") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                printUsingButonName(true,  buttonName +".addActionListener(new ActionListener() {\n" +
                        "            @Override\n" +
                        "            public void actionPerformed(ActionEvent e)\n" +
                        "            {\n" +
                        "               //Your method here\n" +
                        "            }\n" +
                        "        });\n");
            }
        }), gc);
        gc.gridwidth = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.gridwidth = 0;
        add(new JButton(new AbstractAction("add to layout") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                printUsingButonName(true, "*frame/panel/con name*.add(" + buttonName + ");\n");
            }
        }), gc);

    }


    public void printUsingButonName(boolean append, String text)
    {
        if (buttonName == null ||buttonName.equals(""))
        {
            addTextToInfoLabel("stel button name in aub.");
        }
        else
            printText(append, text);
    }
}
