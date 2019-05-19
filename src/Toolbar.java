import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JToolBar {

    private ToolbarListner toolbarListner;
    private JButton jFrameBut, jPanelBut, jButtonBut, jtextFieldBut, jLabelBut, jTextAreaBut, containerBut;

    public Toolbar()
    {
        setFloatable(false);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        addSeparator();
        jFrameBut = new JButton("JFrame");
        containerBut = new JButton("Container");
        jPanelBut = new JButton("JPanel");
        jButtonBut = new JButton("JButton");
        jtextFieldBut = new JButton("JTextField");
        jTextAreaBut = new JButton("JTextArea");
        jLabelBut = new JButton("JLabel");


        jFrameBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (toolbarListner != null)
                    toolbarListner.showFrameMaker();
                else
                    System.out.println("ToolbarListener not found !!!");
            }
        });
        jPanelBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (toolbarListner != null)
                    toolbarListner.showPanelMaker();
                else
                    System.out.println("ToolbarListener not found !!!");
            }
        });
        jButtonBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (toolbarListner != null)
                    toolbarListner.showButtonMaker();
                else
                    System.out.println("ToolbarListener not found !!!");
            }
        });
        jtextFieldBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (toolbarListner != null)
                    toolbarListner.showTextFieldMaker();
                else
                    System.out.println("ToolbarListener not found !!!");
            }
        });
        jTextAreaBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (toolbarListner != null)
                    toolbarListner.showTextAreaMaker();
                else
                    System.out.println("ToolbarListener not found !!!");
            }
        });
        jLabelBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (toolbarListner != null)
                    toolbarListner.showLabelMaker();
                else
                    System.out.println("ToolbarListener not found !!!");
            }
        });
        containerBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (toolbarListner != null)
                {
                    toolbarListner.showContainerMaker();
                }
            }
        });

        add(jFrameBut);
        add(containerBut);
        add(jPanelBut);
        add(jButtonBut);
        add(jtextFieldBut);
        add(jTextAreaBut);
        add(jLabelBut);

        setVisible(true);
    }

    public void setToolbarListener(ToolbarListner toolbarListner)
    {
        this.toolbarListner = toolbarListner;
    }
}
