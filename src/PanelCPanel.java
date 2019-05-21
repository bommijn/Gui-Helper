import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PanelCPanel extends CreatorPanel  {
    private JLabel checkLabel;
    private JTextField panelNameField, widthField, heightField;
    private JButton okButton, layoutBut;
    private JCheckBox conCheck, frameCheck;
    private JComboBox<String> layoutList;

    public PanelCPanel(FormListener formListener, PrintListener printListener) {
        super("Panel creator", formListener, printListener);

        addContainerInputBox(1);
        addFrameNameBut(0);

        checkLabel = new JLabel("<html>Kies waar je het paneel aan wil toevoegen," +
                " maximun 1 keuze. </html>" );
        //checkLabel.setPreferredSize(new Dimension(100,50));
        widthField = new JTextField(5);
        heightField = new JTextField(5);

        heightField.setPreferredSize(new Dimension(95,20));
        widthField.setPreferredSize(new Dimension(95,20));

        conCheck = new JCheckBox("Use container");
        frameCheck = new JCheckBox("Use frame");

        panelNameField = new JTextField(10);
        panelNameField.setMinimumSize(new Dimension(95,20));
        okButton = new JButton("Grab names");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormEvent event = new FormEvent(this, getFrameNameFronInputField(),
                        getContainerNameFronInputField(), panelNameField.getText());
                if (getFormListener() != null)
                {
                    getFormListener().formEventOccured(event);
                    addTextToInfoLabel("Names set <br/>" +
                            "Frame name: " + getFrameName() + "<br/>" +
                            "Container name: " + getContainerName() + "<br/>" +
                            "Panel name: " + getPanelName());
                }
            }
        });


        /*
        LAYOUT MANAGER
         */
        layoutList = new JComboBox<>();
        DefaultComboBoxModel<String> defaultLayoutModel = new DefaultComboBoxModel<>();
        defaultLayoutModel.addElement("");
        defaultLayoutModel.addElement("BoxLayout");
        defaultLayoutModel.addElement("BorderLayout");
        defaultLayoutModel.addElement("CardLayout");
        defaultLayoutModel.addElement("FlowLayout");
        defaultLayoutModel.addElement("GridBagLayout");
        defaultLayoutModel.addElement("GroupLayout");
        defaultLayoutModel.addElement("SpringLayout");
        layoutList.setModel(defaultLayoutModel);


        layoutBut = new JButton("2. set layout");
        layoutBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (layoutList.getSelectedItem() == null || layoutList.getSelectedItem().equals(""))
                {
                   addTextToInfoLabel("Layout eerst instellen.");
                }
                else
                {
                    String choseLayout = "" ;
                    if (layoutList.getSelectedItem() instanceof  String)
                    {
                        choseLayout = (String)layoutList.getSelectedItem();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(layoutBut, "Fatal Layout ERROR contact Siemen\n" +
                                "getSelectedItem() is not an instance of String \n" +
                                "this should really never happen", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String layout;

                    switch (choseLayout)
                    {
                        case "BoxLayout":
                            layout = "new BoxLayout("+ getPanelName() +", **) // **add orientation in constructor";
                            break;

                        case "BorderLayout":
                            layout = "new BorderLayout()";
                            break;

                        case "CardLayout":
                            layout = "new CardLayout()";
                            break;

                        case "FlowLayout":
                            layout = "new FlowLayout()";
                            break;

                        case "GridBagLayout":
                            layout = "new GridBagLayout()";
                            break;

                        case "GroupLayout":
                            layout = "new GroupLayout()";
                            break;
                        case "SpringLayout":
                            layout = "new SpringLayout()";
                            break;

                        default :
                            layout = "!!!!! ERROR !!!!";

                    }
                    printUsingPanelName(true,getPanelName() + ".setLayout(" + layout + ");\n" );
                }
            }
        });
        makeButtons();

        addHelpButton(0,19,7,"Panel creator:\n\n" +
                "USE CONTAINER & USE FRAME :\n" +
                "deze knoppen staan voor of je het paneel aan de container wilt toevoegen of aan het frame (moest je geen container gebruiken)\n" +
                "Er mag maar 1 van deze knoppen tegelijk aangeduid worden anders weet hij niet wat kiezen, deze knoppen schieten in werking als je\n" +
                "op de add to layout button clickt\n\n" +
                "SET LAYOUT :\n" +
                "Stelt de layout in\n\n" +
                "ADD TO LAYOUT:\n" +
                "Voegt het paneel toe aan uw container of frame, (manueel aan een ander paneel toevoegen is ook een optie\n" +
                "het moet niet perse een container of frame zijn)\n" +
                "SET PREFERED SIZE : \n" +
                "voegt de set prefered size methode toe met de parameters meegegeven in de fields.\n" +
                "Deze methode word soms genegeerd door de layoutmanager.\n\n");
        addInfoLabel(20);

    }

    private void makeButtons()
    {
        /*
        row 3
         */
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 0.2;
        gc.weighty =  0.2;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,10);
        add(new JLabel("Panel name: "), gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(panelNameField, gc);

        /*
        row 4
         */
        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(okButton, gc);

        /*
        row 7
         */
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_START;
        add(checkLabel, gc);
        /*
        row 6
         */
        gc.gridwidth = 0;
        gc.gridx = 0;
        gc.gridy = 5;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(conCheck, gc);

        gc.gridx = 1;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.LINE_START;
        add(frameCheck, gc);

        gc.gridx = 0;
        gc.gridy = 6;
        gc.anchor = GridBagConstraints.LINE_START;
        add(new JButton(new AbstractAction("New Panel") {
            @Override
            public void actionPerformed(ActionEvent e) {
                printUsingPanelName(true, "JPanel " + getPanelName() + " = new JPanel();\n");
            }
        }), gc);

        /*
        ROW 8
         */
        gc.gridwidth = 0;
        gc.gridx = 0;
        gc.gridy = 7;
        gc.anchor = GridBagConstraints.LINE_START;
        add(layoutBut, gc);

        gc.gridx = 1;
        gc.gridy = 7;
        gc.anchor = GridBagConstraints.LINE_START;
        add(layoutList, gc);

        /*
        ROW 9
         */
        gc.gridx = 0;
        gc.gridy = 8;
        gc.anchor = GridBagConstraints.LINE_START;
        add(new JButton(new AbstractAction("add to layout") {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (conCheck.isSelected() && frameCheck.isSelected())
                    addTextToInfoLabel("Bijden use Container en use frame zijn checked. Verwijder 1 en probeer opnieuw.");
                else if (conCheck.isSelected())
                    printUsingPanelAndCon(true, getContainerName() + ".add(" + getPanelName() + "); // kan nog moeten bijgewerkt worden afhankelijk van je layout\n");
                else
                    printUsingPanelAndFrame(true, getFrameName() + ".add(" + getPanelName() + ");// kan nog moeten bijgewerkt worden afhankelijk van je layout\n");
            }
        }), gc);



        gc.gridx = 0;
        gc.gridy = 9;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("Panel Height:"), gc);

        gc.gridx = 1;
        gc.gridy = 9;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Panel Width:"), gc);

        gc.gridx = 0;
        gc.gridy = 10;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_START;
        add(heightField, gc);

        gc.gridx = 1;
        gc.gridy = 10;
        gc.anchor = GridBagConstraints.LINE_END;
        add(widthField, gc);

        gc.gridx = 0;
        gc.gridy = 11;
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
                    printUsingPanelName(true, getPanelName() + ".setPreferredSize(new Dimension("+width + "," + height + "));\n");

                } catch (NumberFormatException n)
                {
                    addTextToInfoLabel("Panel height or width can only accept numbers !! It also cant be empty, try again");
                }
            }
        }), gc);

    }
}
