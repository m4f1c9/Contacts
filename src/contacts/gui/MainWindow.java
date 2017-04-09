package contacts.gui;

import contacts.Contact;
import contacts.parser.Parser;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.xml.bind.JAXBException;

public class MainWindow {

    private JTable table;
    private ContactsTableModel contactsTableModel;
    JFrame frame;

    public void showMainWindow() {
        frame = new JFrame();
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));

        table = createTable();
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setSize(800, 420);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

    }

    private JTable createTable() {
        contactsTableModel = new ContactsTableModel();
        JTable table = new JTable(contactsTableModel);
        table.setRowHeight(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(80);
        table.setAutoCreateRowSorter(true);

        TableCellEditor existingEditor = table.getDefaultEditor(Object.class);
        ((DefaultCellEditor) existingEditor).setClickCountToStart(1);

        JPanel centerPanel = new JPanel();
        centerPanel.add(new JScrollPane(table));
        return table;
    }

    private JPanel createButtonPanel() {
        JButton addRow = new JButton("Новый контакт");
        addRow.addActionListener(e -> contactsTableModel.addRow(new Contact()));

        JButton deleteRow = new JButton("Удалить контакт");
        deleteRow.addActionListener(e -> {
            if (table.getSelectedRow() >= 0) {
                contactsTableModel.deleteRow(table.convertRowIndexToModel(table.getSelectedRow()));
            }
        });

        JButton save = new JButton("Сохранить контакты");
        save.addActionListener((e) -> {
            FileDialog fileDialog = new FileDialog(frame,
                    "Save", FileDialog.SAVE);
            fileDialog.setMultipleMode(false);
            fileDialog.setVisible(true);
            if (fileDialog.getFile() != null) {
                try {
                    new Parser().saveContacts(new File(fileDialog.getDirectory(), fileDialog.getFile()), contactsTableModel.getContacts());
                } catch (JAXBException ex) {
                    JOptionPane.showMessageDialog(frame, "Не удалось сохранить контакты");
                }
            }
        });

        JButton open = new JButton("Открыть контакты");
        open.addActionListener((e) -> {
            FileDialog fileDialog = new FileDialog(frame,
                    "Open", FileDialog.LOAD);
            fileDialog.setMultipleMode(false);
            fileDialog.setVisible(true);
            if (fileDialog.getFile() != null){
                try {
                    ArrayList<Contact> list = new Parser().getContacts(new File(fileDialog.getDirectory(), fileDialog.getFile()));
                    contactsTableModel.getContacts().clear();
                    
                    list.forEach((contact) -> {
                        contactsTableModel.addRow(contact);
                    });
                    
                    table.revalidate();
                } catch (JAXBException ex) {
                   JOptionPane.showMessageDialog(frame, "Не удалось открыть контакты");
                }
            }
        });

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        southPanel.add(addRow);
        southPanel.add(deleteRow);
        southPanel.add(save);
        southPanel.add(open);

        return southPanel;
    }
}
