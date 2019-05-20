import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCPanel extends CreatorPanel  {
    private JLabel checkLabel;
    private JTextField conNameField, panelNameField;
    private JButton okButton, layoutBut;
    private JCheckBox conCheck, frameCheck;
    private JComboBox<String> layoutList;

    public PanelCPanel() {
        super("Panel creator");

        addContainerInputBox();
        addFrameNameBut();
        checkLabel = new JLabel("<html>Kies waar je het paneel aan wil toevoegen," +
                " maximun 1 keuze. </html>" );
        //checkLabel.setPreferredSize(new Dimension(100,50));

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
                            layout = "new BoxLayout()";
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
        //TODO set infoBox
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

    }
}
