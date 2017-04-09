package contacts.gui;

import contacts.Contact;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ContactsTableModel extends AbstractTableModel {

    private ArrayList<Contact> contacts;

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
    

    public ContactsTableModel() {
        this.contacts = new ArrayList<Contact>();
    }

    @Override
    public int getRowCount() {
        return contacts.size();
    }

    public void addRow(Contact contact) {
        int newRowIndex = contacts.size();
        contacts.add(contact);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void deleteRow(int row) {
        if (row >= 0 && row < contacts.size()) {
            contacts.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Имя";
            case 1:
                return "Фамилия";
            case 2:
                return "Отчество";
            case 3:
                return "Дата рождения";
            case 4:
                return "Адрес";
            case 5:
                return "Номер телефона";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            default:
                return Object.class;
        }

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return contacts.get(rowIndex).getFirstName();
            case 1:
                return contacts.get(rowIndex).getLastName();
            case 2:
                return contacts.get(rowIndex).getMiddleName();
            case 3:
                return contacts.get(rowIndex).getBirthday();
            case 4:
                return contacts.get(rowIndex).getAddress();
            case 5:
                return contacts.get(rowIndex).getPhoneNumber();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                contacts.get(rowIndex).setFirstName((String) aValue);
                break;
            case 1:
                contacts.get(rowIndex).setLastName((String) aValue);
                break;
            case 2:
                contacts.get(rowIndex).setMiddleName((String) aValue);
                break;
            case 3:
                contacts.get(rowIndex).setBirthday((String) aValue);
                break;
            case 4:
                contacts.get(rowIndex).setAddress((String) aValue);
                break;
            case 5:
                contacts.get(rowIndex).setPhoneNumber((String) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);

    }
}
