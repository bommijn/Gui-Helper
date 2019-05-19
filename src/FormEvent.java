import java.util.EventObject;

public class FormEvent extends EventObject {
    private String frameName, containerName;

    public FormEvent(Object source, String frameName, String containerName)
    {
        super(source);
        this.frameName = frameName;
        this.containerName = containerName;
    }


    public String getFrameName()
    {
        return frameName;

    }

    public void setFrameName(String frameName)
    {
        this.frameName = frameName;
    }

    public String getContainerName()
    {
        return containerName;
    }

    public void setContainerName(String containerName)
    {
        this.containerName = containerName;
    }


}
