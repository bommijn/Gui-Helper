import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainWindow extends JFrame {
    private TextPanel textPanel;
    private Toolbar toolbar;
    private FrameCPanel frameCPanel;
    private ContainerCPanel containerCPanel;



    public MainWindow()
    {
        super("Gui helper");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        toolbar = new Toolbar();
        frameCPanel = new FrameCPanel();
        containerCPanel = new ContainerCPanel();

        frameCPanel.addFormListener(new FormListener(){
            public void formEventOccured(FormEvent event) {
                String frameName = event.getFrameName();
                String containerName = event.getContainerName();

                frameCPanel.setFrameName(frameName);
                frameCPanel.setContainerName(containerName);

                containerCPanel.setContainerName(containerName);
                containerCPanel.setFrameName(frameName);

            }
        });
        frameCPanel.addPrintListener(new PrintListener() {
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
        });

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
}
