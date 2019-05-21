import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContainerCPanel extends CreatorPanel {
    private JComboBox<String> layoutList;
    private JButton layoutBut;
    public ContainerCPanel(FormListener formListener, PrintListener printListener)
    {
        super("Container Creator", formListener, printListener);
        addFrameNameBut(0);
        addContainerInputBox(1);
        addOkButtonForInputFields(2);

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
                    JOptionPane.showMessageDialog(layoutBut, "Layout instellen aub", "Error", JOptionPane.ERROR_MESSAGE);
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
                                "getSelectedItem() is not an instance of String", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String layout;

                    switch (choseLayout)
                    {
                        case "BoxLayout":
                            layout = "new BoxLayout("+getContainerName()+", **) // **add orientation in constructor";
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
                    printUsingContainerName(true,getContainerName() + ".setLayout(" + layout + ");\n" );
                }
            }
        });

        createButtons();
        addHelpButton(0,12,8,"Container creator:\n\n" +
                "GRAB NAMES:\n" +
                "Deze knop grabbed en set de names die ingegeven zijn in de velden boven de knop.\n\n" +
                "CREATE AND SET : \n" +
                "deze knop gaat de container constructen en automatish linken aan het frame\n" +
                "om goed te werken moet de framename correct zijn ingesteld.\n\n" +
                        "SET LAYOUT :\n" +
                        "stelt de layout in met de gekozen layout.\n\n" +
                        "ADD OBJECT TO THIS : \n" +
                        "Dit gaat de .add op de container gebruiken. Het item dat moet toegevoegd worden moet je zelf typen.");
        addInfoLabel(20);

    }

    private void createButtons()
    {
        /*
        4de rij
         */
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.1;
        gc.weighty = 0.2;
        gc.gridy = 3;
        gc.gridx = 0;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(new JButton(new AbstractAction("1. create and set") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                printUsingFrameAndCon(false, "Container " + getContainerName() + " = " + getFrameName() + ".getContentPane();\n");
            }
        }), gc);


        /*
        5de rij
         */
        gc.gridwidth = 1;
        gc.weightx = 0.1;
        gc.weighty = 0.3;
        gc.gridy = 4;
        add(layoutBut, gc);

        gc.weightx = 0.1;
        gc.weighty = 0.3;
        gc.gridy = 4;
        gc.gridx = 1;
        gc.gridwidth = 1;
        add(layoutList, gc);

        /*
        6de rij
         */

        gc.weightx = 0.1;
        gc.weighty = 0.3;
        gc.gridy = 5;
        gc.gridx = 0;
        gc.gridwidth = 2;
        add(new JButton(new AbstractAction("Add object to this") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                printUsingContainerName(true, getContainerName() + ".add(objName); // vervang objName door hetgene dat moet \n" +
                      "//toegevoegd worden. "  );
            }
        }), gc);


    }
}
