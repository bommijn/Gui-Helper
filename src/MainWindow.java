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
        frameCPanel = new FrameCPanel();
        containerCPanel = new ContainerCPanel();

        frameCPanel.addFormListener(formListener);
        containerCPanel.addFormListener(formListener);

        frameCPanel.addPrintListener(printer);
        containerCPanel.addPrintListener(printer);

        toolbar.setToolbarListener(new ToolbarListner() {
           //TODO implement methods here
            @Override
            public void showFrameMaker()
            {

                remove(containerCPanel);
                add(frameCPanel, BorderLayout.WEST);

                revalidate();
                repaint();
            }

            @Override
            public void showContainerMaker()
            {
                remove(frameCPanel);
                add(containerCPanel, BorderLayout.WEST);

                revalidate();
                repaint();
            }

            @Override
            public void showPanelMaker()
            {

            }

            @Override
            public void showButtonMaker()
            {

            }

            @Override
            public void showTextFieldMaker()
            {

            }

            @Override
            public void showLabelMaker()
            {

            }

            @Override
            public void showTextAreaMaker()
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

            frameCPanel.setFrameName(frameName);
            frameCPanel.setContainerName(containerName);

            containerCPanel.setContainerName(containerName);
            containerCPanel.setFrameName(frameName);
        }
    }
}


