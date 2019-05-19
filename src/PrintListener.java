import java.util.EventListener;

public interface PrintListener extends EventListener {
    void printAppend(PrintEvent e);
    void printClear(PrintEvent e);
}
