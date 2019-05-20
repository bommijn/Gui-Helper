import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameCPanel extends CreatorPanel {
    private JComboBox<String> layoutList;
    private JButton layoutBut, makeFormBut, closeEventBut, packBut;


    public FrameCPanel()
    {
        super("Frame Creator");


        super.addFrameNameBut();
        super.addContainerInputBox();
        super.addOkButtonForInputFields();
        addInfoLabel(10);



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




        /*
        make form button
         */
        makeFormBut = new JButton("1.Make Frame");
        makeFormBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               printUsingFrameName(false,"JFrame " + getFrameName() +" = new JFrame(*titleHere*);\n");
            }
        });

        /*
        close button
         */
        closeEventBut = new JButton("2. Add close event");
        closeEventBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                printUsingFrameName(true,getFrameName() + ".addWindowListener(new WindowAdapter()\n" +
                        "            {\n" +
                        "                public void windowClosing(WindowEvent e)\n" +
                        "                {\n" +
                        "                    // Your methode here to close\n" +
                        "                }\n" +
                        "            }\n" +
                        "        );\n");
            }
        });

        /*
        layout button
        !!! ALSO HANDELS LAYOUTLIST
         */
        layoutBut = new JButton("3. set layout");
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
                    printUsingFrameName(true,getFrameName() + ".setLayout(" + layout + ");\n" );
                }
            }
        });

        /*
        PACK BUTTON !!!!!!!! NOT USED ATM !!!!!!!
         */
        packBut = new JButton("4. pack");
        packBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                printUsingFrameName(true, getFrameName() + ".pack();");
            }
        });

        layoutComponents();
    }


    private void layoutComponents()
    {

        /*
        4de rij
         */
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 0.1;
        gc.weighty = 0.2;
        gc.gridy = 3;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(makeFormBut, gc);

        /*
        5de rij
         */
        gc.weightx = 0.1;
        gc.weighty = 0.2;
        gc.gridy = 4;
        gc.gridwidth = 2;
        add(closeEventBut, gc);

        /*
        6de rij
         */
        gc.weightx = 0.1;
        gc.weighty = 0.2;
        gc.gridy = 5;
        gc.gridwidth = 1;
        add(layoutBut, gc);

        gc.weightx = 0.1;
        gc.weighty = 0.2;
        gc.gridy = 5;
        gc.gridx = 1;
        gc.gridwidth = 1;
        add(layoutList, gc);

        /*
        7de rij
         */
        gc.weightx = 0.1;
        gc.weighty = 0.2;
        gc.gridy = 6;
        gc.gridx = 0;
        gc.gridwidth = 1;
        add(new JButton(new AbstractAction("4. Pack") {
            public void actionPerformed(ActionEvent e) {
                printUsingFrameName(true, getFrameName() + ".pack();\n");
            }
        }), gc);

        /*
        8de rij
         */
        gc.weightx = 0.1;
        gc.weighty = 0.2;
        gc.gridy = 7;
        add(new JButton(new AbstractAction("5. set visible") {
            public void actionPerformed(ActionEvent e) {
                printUsingFrameName(true, getFrameName() + ".setVisible(true);\n");
            }
        }), gc);

         /*
        9de rij
         */
        gc.weightx = 0.1;
        gc.weighty = 0.2;
        gc.gridy++;
        add(new JButton(new AbstractAction("Exit methode") {
            public void actionPerformed(ActionEvent e) {
                printText(true, "private void quitClicked()\n" +
                        "    {\n" +
                        "        System.exit(0);\n" +
                        "    }\n");
            }
        }), gc);


         /*
        10de rij
         */
        gc.weightx = 0.1;
        gc.weighty = 15;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridy++;
        add(new JButton(new AbstractAction("INFO") {
            public void actionPerformed(ActionEvent e) {
                printText(false, "Altijd starten met een Frame aan te maken,\n" +
                        " daarna moet er een default close operation aan toe gevoegd worden\n" +
                        "Je kan dit doen door de knop hier te gebruiken of door dit toe te voegen:\n\n" +
                        "*FRAME NAME*.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); \n\n" +
                        "De methode pack moet altijd onderaan komen achter alle dingen dat je toevoegd\n" +
                        " en net voor de *FRAME NAME*.setVisable() methode\n" +
                        "set visable is altijd het allerlaatse dat he uitvoerd.");
            }
        }), gc);

    }



}
