import com.sun.deploy.util.BlackList;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
public class Gui {


    private JPanel panels[];
    private JPanel frameCreatorPanel;
    private JFrame frame;
    private JTextPane textPane;
    
    private Container con;

    public Gui()
    {

        panels = new JPanel[5];
        makeGui();
    }

    private void makeGui()
    {

        frame = new JFrame("Gui helper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(350,450));

        con = new Container();
        con = frame.getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.setVisible(true);
        createPanels();



        /*
        Row 1
         */
        JLabel nameInfoLabel = new JLabel("Naam container / panel");
        nameInfoLabel.setBackground(Color.YELLOW);
        panels[0].add(nameInfoLabel);

        panels[0].add(Box.createRigidArea(new Dimension(15,0)));

        JTextArea nameTextArea =  new JTextArea();
        nameTextArea.setMaximumSize(new Dimension(150,20));
        panels[0].add(nameTextArea);

        /*
        Row 2
         */
        JLabel infoLabel = new JLabel("Wat heb je nodig ? ");
        panels[1].add(infoLabel);

        /*
        Row 3
         */
        JButton jFrameBut = new JButton("JFrame");
        jFrameBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                showFrameCreator();
            }
        });
        panels[2].add(jFrameBut);
        panels[2].add(Box.createRigidArea(new Dimension(15,0)));

        JButton jPanelBut = new JButton("JPanel");
        jPanelBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //showJPanelCreator();
            }
        });
        panels[2].add(jPanelBut);

        /*
        Row 4
         */
        JButton jButtonBut = new JButton("JButton");
        jButtonBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //showButtonCreator();
            }
        });
        panels[3].add(jButtonBut);
        panels[3].add(Box.createRigidArea(new Dimension(15,0)));

        JButton jTextFieldBut = new JButton("JTextField");
        jTextFieldBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //showTextFieldCreator();
            }
        });
        panels[3].add(jTextFieldBut);
        /*
        Row 5
         */
        JButton jLabelBut = new JButton("JLabel");
        jLabelBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //showJLabelCreator();
            }
        });
        panels[4].add(jLabelBut);
        panels[4].add(Box.createRigidArea(new Dimension(15,0)));

        JButton jTextAreaBut = new JButton("JTextArea");
        jTextAreaBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //showContainerCreator();
            }
        });
        panels[4].add(jTextAreaBut);






        frame.pack();
        frame.setVisible(true);
    }

    private void createPanels()
    {
        for (int i = 0; i < panels.length; i++)
        {
            panels[i] = new JPanel();
            panels[i].setLayout(new BoxLayout(panels[i], BoxLayout.X_AXIS));
            con.add(Box.createRigidArea(new Dimension(0,15)));
            con.add(panels[i]);
        }
    }

    private void setConVisibility(boolean vis)
    {
        con.setVisible(vis);
    }

    private void resetToMainScreenView()
    {

        setConVisibility(true);
        for (JPanel pan : panels)
        {
            pan.setVisible(true);
        }
    }

    private void hideMainPanels()
    {
        int i = 0;
        for (JPanel pan : panels)
        {
            pan.setVisible(false);
            System.out.println(i);
            i++;
        }
    }
    private void showFrameCreator()
    {
        hideMainPanels();
        frame.setMinimumSize(new Dimension(600,700));

        frameCreatorPanel = new JPanel();
        frameCreatorPanel.setLayout(new BoxLayout(frameCreatorPanel, BoxLayout.X_AXIS));
        con.add(frameCreatorPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        frameCreatorPanel.add(buttonPanel);



        GridBagConstraints backCons = new GridBagConstraints();
        JButton goBackBut = new JButton("Go back");
        goBackBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetToMainScreenView();
                frameCreatorPanel.setVisible(false);
            }
        });
        backCons.gridy = 3;
        backCons.gridx = 1;
        backCons.anchor = GridBagConstraints.WEST;
        buttonPanel.add(goBackBut,backCons);


        GridBagConstraints makeButCons = new GridBagConstraints();
        JButton makeBut = new JButton("1.make frame");
        makeBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textPane.setText("JFrame frame = new JFrame(*titleHere*);\n" );
            }
        });
        makeButCons.gridy = 0;
        makeButCons.gridx = 0;
        makeButCons.anchor = GridBagConstraints.WEST;
        buttonPanel.add(makeBut,makeButCons);

        GridBagConstraints windowLisCons = new GridBagConstraints();
        JButton windowLisBut = new JButton("2.CloseEvent");
        windowLisBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textPane.setText(textPane.getText() + "frame.addWindowListener(new WindowAdapter()\n" +
                        "            {\n" +
                        "                public void windowClosing(WindowEvent e)\n" +
                        "                {\n" +
                        "                    // Your methode here to close\n" +
                        "                }\n" +
                        "            }\n" +
                        "        );\n");
            }
        });
        windowLisCons.gridy = 0;
        windowLisCons.gridx = 1;
        windowLisCons.anchor = GridBagConstraints.WEST;
        buttonPanel.add(windowLisBut,windowLisCons);

        GridBagConstraints packCons = new GridBagConstraints();
        JButton packBut = new JButton("3. Pack");
        packBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textPane.setText(textPane.getText() + "frame.pack(); \n");
            }
        });
        packCons.gridy = 1;
        packCons.gridx = 0;
        packCons.anchor = GridBagConstraints.WEST;
        buttonPanel.add(packBut,packCons);

        GridBagConstraints visCons = new GridBagConstraints();
        JButton visBut = new JButton("4. Set visible");
        visBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textPane.setText(textPane.getText() + "frame.setVisible(true); \n");
            }
        });
        visCons.gridy = 1;
        visCons.gridx = 1;
        visCons.anchor = GridBagConstraints.WEST;
        buttonPanel.add(visBut,visCons);


        GridBagConstraints exitCons= new GridBagConstraints();
        JButton exitBut = new JButton("5. Exit button code");
        exitBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textPane.setText("private void quitClicked()\n"+
                "{\n"+
                    "System.exit(0);\n"+
                "};\n");
            }
        });
        exitCons.gridy = 2;
        exitCons.gridx = 0;
        exitCons.anchor = GridBagConstraints.WEST;
        buttonPanel.add(exitBut,exitCons);

        GridBagConstraints copyCons = new GridBagConstraints();
        JButton copyBut = new JButton("Copy to clipboard");
        copyBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String myString = textPane.getText();
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });
        copyCons.gridy = 3;
        copyCons.gridx = 0;
        copyCons.anchor = GridBagConstraints.WEST;
        buttonPanel.add(copyBut,copyCons);


        JPanel textAreaPanel = new JPanel();
        frameCreatorPanel.add(textAreaPanel,BorderLayout.CENTER);

        textPane = new JTextPane();
        textAreaPanel.add(textPane);

    }









}
