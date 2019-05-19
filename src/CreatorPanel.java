import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class CreatorPanel extends JPanel {

    private String frameName, containerName;
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


    public void addOkButtonForInputFields()
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
            containerName = JOptionPane.showInputDialog(this,
                    "Set container name first", null);
            if (getFormListener() != null)
            {
                if (getFrameName() == null)
                    updateForm("*Frame name not set*", getContainerName());

                else
                    updateForm(getFrameName(), getContainerName());

            }
            else
            {
                JOptionPane.showMessageDialog(this,"Fatal Error contact Siemen", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
             frameName = JOptionPane.showInputDialog(this,
                    "Set frameName first", null);
            if (getFormListener() != null)
            {
                if (getContainerName() == null)
                    updateForm(frameName, "");

                else
                    updateForm(frameName, getContainerName());
            }

            else
            {
                JOptionPane.showMessageDialog(this,"Fatal Error contact Siemen", "Error", JOptionPane.ERROR_MESSAGE);
            }
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





    public void updateForm(String frameName, String containerName)
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
}
