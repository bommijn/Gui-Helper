import java.util.EventObject;

public class FormEvent extends EventObject {
    private String frameName, containerName, panelName;

    public FormEvent(Object source, String frameName, String containerName, String panelName )
    {
        super(source);
        this.frameName = frameName;
        this.containerName = containerName;
        this.panelName = panelName;
    }

    public FormEvent(Object source, String frameName, String containerName) {
        super(source);
        this.frameName = frameName;
        this.containerName = containerName;
    }

    public String getPanelName() {
        return panelName;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName;
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
