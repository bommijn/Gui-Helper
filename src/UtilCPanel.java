import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UtilCPanel extends CreatorPanel {
    public UtilCPanel(FormListener formListener, PrintListener printListener)
    {
        super("Util panel", formListener, printListener);
        addHelpButton(0,18,7,"Util panel:\n\n" +
                        "IMPORTS : \n" +
                "Deze knop geeft je alle inports dat je nodig hebt moest je elke functie van deze applicatie gebruiken\n\n");

        makeButtons();
        addInfoLabel(20);
    }

    private void makeButtons()
    {
        GridBagConstraints gc = new GridBagConstraints();
       gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        gc.gridy = 0;
        gc.gridx = 0;
        add(new JButton(new AbstractAction("imports") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                printText(true, "import javax.swing.*;\n" +
                        "import java.awt.event.*;\n" +
                        "import java.awt.Dimension;\n");
            }
        }),gc);

        gc.gridy++;

    }
}
