import java.util.EventObject;

public class PrintEvent extends EventObject {
    private String text;

    public PrintEvent(Object source, String text)
    {
        super(source);
        this.text = text;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
