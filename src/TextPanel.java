import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextPanel extends JPanel {
    private JTextArea textArea;
    public TextPanel()
    {
        textArea = new JTextArea();
        JButton copyButton = new JButton("Copy to clipboard");
        setLayout(new BorderLayout());

        JPanel butnPanel = new JPanel(new BorderLayout());


        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String myString = textArea.getText();
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });

        JButton clearBut = new JButton("Clear");
        clearBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textArea.setText("");
            }
        });

        add(butnPanel, BorderLayout.SOUTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        butnPanel.add(copyButton, BorderLayout.CENTER);
        butnPanel.add(clearBut, BorderLayout.AFTER_LINE_ENDS);

    }

    public void appendText(String text)
    {
        textArea.setText(textArea.getText() + text);
    }

    public void clearAndSetText(String text)
    {
        textArea.setText(text);
    }
}
