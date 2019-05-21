import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class MainWindow extends JFrame {
    private TextPanel textPanel;
    private Toolbar toolbar;
    private FrameCPanel frameCPanel;
    private ContainerCPanel containerCPanel;
    private PanelCPanel panelCPanel;
    private ButtonCPanel buttonCPanel;
    private  TextFieldCPanel textFieldCPanel;
    private FormFiller formListener;
    private Printer printer;



    public MainWindow()
    {
        super("Gui helper");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        printer = new Printer();
        formListener = new FormFiller();
        textPanel = new TextPanel();
        toolbar = new Toolbar();
        frameCPanel = new FrameCPanel(formListener,printer);
        containerCPanel = new ContainerCPanel(formListener,printer);
        panelCPanel = new PanelCPanel(formListener, printer);
        buttonCPanel = new ButtonCPanel(formListener,printer);
        textFieldCPanel = new TextFieldCPanel(formListener, printer);

        toolbar.setToolbarListener(new ToolbarListner() {
           //TODO implement methods here
            @Override
            public void showFrameMaker()
            {

                remove(containerCPanel);
                remove(panelCPanel);
                remove(textFieldCPanel);
                add(frameCPanel, BorderLayout.WEST);

                revalidate();
                repaint();
            }

            @Override
            public void showContainerMaker()
            {
                remove(frameCPanel);
                remove(panelCPanel);
                remove(textFieldCPanel);
                remove(buttonCPanel);

                add(containerCPanel, BorderLayout.WEST);
                revalidate();
                repaint();
            }

            @Override
            public void showPanelMaker()
            {
                remove(frameCPanel);
                remove(containerCPanel);
                remove(textFieldCPanel);
                remove(buttonCPanel);

                add(panelCPanel, BorderLayout.WEST);
                revalidate();
                repaint();
            }

            @Override
            public void showButtonMaker()
            {
                remove(frameCPanel);
                remove(panelCPanel);
                remove(textFieldCPanel);
                remove(containerCPanel);

                add(buttonCPanel, BorderLayout.WEST);
                revalidate();
                repaint();

            }

            @Override
            public void showTextFieldMaker()
            {
                remove(frameCPanel);
                remove(panelCPanel);
                remove(textFieldCPanel);
                remove(buttonCPanel);

                add(textFieldCPanel, BorderLayout.WEST);
                revalidate();
                repaint();
            }

            @Override
            public void showLabelMaker()
            {

            }


        });

        add(textPanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);


        setSize(650,750);
        setVisible(true);


    }

    private class Printer implements PrintListener {


        @Override
        public void printAppend(PrintEvent e)
        {
            textPanel.appendText(e.getText());
        }

        @Override
        public void printClear(PrintEvent e)
        {
            textPanel.clearAndSetText(e.getText());
        }
    }

    private class FormFiller implements FormListener {

        @Override
        public void formEventOccured(FormEvent e)
        {
            String frameName = e.getFrameName();
            String containerName = e.getContainerName();
            String panelName = e.getPanelName();

            frameCPanel.setFrameName(frameName);
            frameCPanel.setContainerName(containerName);

            containerCPanel.setContainerName(containerName);
            containerCPanel.setFrameName(frameName);
            containerCPanel.setPanelName(panelName);

            panelCPanel.setContainerName(containerName);
            panelCPanel.setFrameName(frameName);
            panelCPanel.setPanelName(panelName);
        }
    }
}


