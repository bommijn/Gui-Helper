import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContainerCPanel extends CreatorPanel {
    private JComboBox<String> layoutList;
    private JButton layoutBut;
    public ContainerCPanel()
    {
        super("Container Creator");
        addFrameNameBut();
        addContainerInputBox();
        addOkButtonForInputFields();

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
                    printUsingContainerName(true,getContainerName() + ".setLayout(" + layout + ");\n" );
                }
            }
        });

        createButtons();
        addInfoLabel(5);

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
                printUsingFormAndCon(false, "Container " + getContainerName() + " = " + getFrameName() + ".getContentPane();\n");
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


    }
}
