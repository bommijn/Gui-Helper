import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameCPanel extends CreatorPanel {
    private JComboBox<String> layoutList;
    private JButton layoutBut, makeFormBut, closeEventBut, packBut;
    JTextField heightField, widthField;


    public FrameCPanel(FormListener formListener, PrintListener printListener)
    {
        super("Frame Creator",formListener, printListener);


        super.addFrameNameBut(0);
        super.addContainerInputBox(1);
        super.addOkButtonForInputFields(2);
        addInfoLabel(20);

        heightField = new JTextField();
        widthField = new JTextField();


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
                            layout = "new BoxLayout("+ getFrameName()+", **) // **add orientation in constructor";
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


        packBut = new JButton("4. pack");
        packBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                printUsingFrameName(true, getFrameName() + ".pack();");
            }
        });

        layoutComponents();
        addHelpButton(0,12,6,"Frame Creator info: \n" +
                "GRAB NAMES:\n" +
                "Deze knop grabbed en set de names die ingegeven zijn in de velden boven de knop.\n\n" +
                "MAKE FRAME:\n" +
                "Deze knop maakt het frame, het frame gaat niet weergegeven worden als een globaal variable\n" +
                "Om dat te bekomen moet je nog private JFrame *FRAMENAME*; vanboven in je class toevoegen.\n\n" +
                "ADD CLOSE EVENT:\n" +
                "Voegt een close event toe aan de frame\n\n" +
                "SET LAYOUT:\n" +
                "stelt de layout in met de gekozen layout.\n\n" +
                "PACK:\n" +
                "Packs? het frame zodat alles mooi op zijn plaats komt. dit lijntje code komt NA alles is toegevoegd aan de layout.\n\n" +
                "SET VISIBLE:\n" +
                "zet het frame visible. Deze methode moet altijd op het allerlaatse komen\n\n" +
                "SET PREFERED SIZE : \n" +
                "stelt de prefered size in met de parameters erboven meegegeven. \n\n" +
                "EXIT METHODE:\n" +
                "geeft u de exit methode die in je close event moet komen.\n\n" );

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


        gc.gridy ++;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("Frame Height:"), gc);

        gc.gridx = 1;

        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Frame Width:"), gc);

        gc.gridx = 0;
        gc.gridy++;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_START;
        add(heightField, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(widthField, gc);

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
                    printUsingFrameName(true, getFrameName() + ".setPreferredSize(new Dimension("+width + "," + height + "));\n");

                } catch (NumberFormatException n)
                {
                    addTextToInfoLabel("frame height or width can only accept numbers !! It also cant be empty, try again");
                }
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

    }



}
