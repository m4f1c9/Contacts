package contacts;

import contacts.gui.MainWindow;
import javax.swing.SwingUtilities;

public class MainClass {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().showMainWindow());
    }
    
}
