import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class CreatorPanel extends JPanel {

    private String frameName, containerName;
    private JLabel infoLabel;
    private FormListener formListener;
    private FormEvent event;
    private PrintListener printListener;
    private JTextField conNameField, nameField;

    public CreatorPanel(String title)
    {
        this.frameName = null;
        this.containerName = null;


        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        Border innerBorder = BorderFactory.createTitledBorder(title);
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        setLayout(new GridBagLayout());
    }



    public void addFrameNameBut()
    {
        nameField = new JTextField(10);
        JLabel nameLabel = new JLabel("Frame name:");
        nameField.setMinimumSize(new Dimension(95,20));
        GridBagConstraints gc = new GridBagConstraints();

        gc.weighty = 0.1;
        gc.weightx = 0.1;

        /*
        First row
         */
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,10);
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(nameField, gc);

    }

    public void addContainerInputBox()
    {

        conNameField = new JTextField(10);
        JLabel conLabel = new JLabel("Container name:");
        conNameField.setMinimumSize(new Dimension(95,20));
       /*
       Second Row
        */
        GridBagConstraints gc = new GridBagConstraints();
        gc.weighty = 0.1;
        gc.weightx = 0.1;

        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(conLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(conNameField, gc);
    }


    protected void addOkButtonForInputFields()
    {
        JButton getNamesBut = new JButton("Grab names");

        GridBagConstraints gc = new GridBagConstraints();

        getNamesBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frameName = nameField.getText();
                containerName = conNameField.getText();

                event = new FormEvent(this, frameName, containerName);
                if(getFormListener() != null)
                {
                    getFormListener().formEventOccured(event);
                    addTextToInfoLabel("names have been set<br/>" +
                            "frame name: " + frameName + "<br/>"+
                            "Container name: " + containerName);
                    revalidate();
                }
            }
        });
        gc.weightx = 0.1;
        gc.weighty = 0.2;
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,5);
        add(getNamesBut, gc);
    }

    public void addInfoLabel(int gridY)
    {
        infoLabel = new JLabel("info: ");
        Font font = new Font(Font.DIALOG,Font.BOLD,12);
        infoLabel.setFont(font);
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 0.1;
        gc.weighty = 2;
        gc.gridx = 0;
        gc.gridy = gridY;
        gc.gridwidth =2;
        gc.gridheight = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,5);
        add(infoLabel, gc);
    }

    public void addTextToInfoLabel(String text)
    {
        infoLabel.setText("<html>Info:  " + text + "</html>");
    }



    public void printText(boolean append, String text)
    {
        PrintEvent printEvent = new PrintEvent(this, text);
        if (append)
            printListener.printAppend(printEvent);
        else
            printListener.printClear(printEvent);
    }

    /**
     * stuurt text naar het textfield waar de naam van het frameName nodig is
     * @param append
     * @param text
     */
    public void printUsingContainerName(boolean append, String text)
    {
        if (getContainerName() == null)
        {
            addTextToInfoLabel("Please set your container name");
        }
        else
        {
            PrintEvent printEvent = new PrintEvent(this, text);
            if (append)
            {
                getPrintListener().printAppend(printEvent);
            }
            else
                getPrintListener().printClear(printEvent);
        }
    }

    /**
     * stuurt text naar het textfield waar de naam van het frameName nodig is
     * @param append
     * @param text
     */
    public void printUsingFormName(boolean append, String text)
    {
        if (getFrameName() == null)
        {
            addTextToInfoLabel("Please set your frame name.");
        }
        else
        {
            PrintEvent printEvent = new PrintEvent(this, text);
            if (append)
            {
                getPrintListener().printAppend(printEvent);
            }
            else
            getPrintListener().printClear(printEvent);
        }
    }


    /**
     * stuurt text naar het textfield waar de naam van het frameName en container nodig is.
     * @param append
     * @param text
     */
    public void printUsingFormAndCon(boolean append, String text)
    {
        if (getFrameName() == null || getContainerName() == null)
        {
            addTextToInfoLabel("Please set your" +
                    "<br/>" +
                    "Container and frame name");
        }
        else
        {
            PrintEvent printEvent = new PrintEvent(this, text);
            if (append)
            {
                getPrintListener().printAppend(printEvent);
            }
            else
                getPrintListener().printClear(printEvent);
        }
    }


    public PrintListener getPrintListener()
    {
        return printListener;
    }

    public void addPrintListener(PrintListener printListener)
    {
        this.printListener = printListener;
    }

    public void addFormListener(FormListener listener)
    {
        formListener = listener;
    }

    public FormListener getFormListener()
    {
        return formListener;
    }

    public String getFrameName()
    {
        return frameName;
    }

    public String getContainerName()
    {
        return containerName;
    }


    public void setFrameName(String frameName)
    {
        this.frameName = frameName;
    }


    public void setContainerName(String containerName)
    {
        this.containerName = containerName;
    }



     /* public void updateForm(String frameName, String containerName)
    {
        if (event == null)
        {
            event =  new FormEvent(this, frameName, containerName);
            formListener.formEventOccured(event);
        }
        else if (!frameName.equals("") && !containerName.equals(""))
        {
            event.setContainerName(containerName);
            event.setFrameName(frameName);
            formListener.formEventOccured(event);
        }
        else if (!frameName.equals(""))
        {
            event.setContainerName(containerName);
            formListener.formEventOccured(event);
        }
        else
        {
            event.setFrameName(frameName);
            formListener.formEventOccured(event);
        }
    }*/
}
