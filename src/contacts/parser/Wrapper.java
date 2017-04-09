package contacts.parser;

import contacts.Contact;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "Contacts")
public class Wrapper {

    @XmlElement(name = "Contact")
    private ArrayList<Contact> contact;

    @XmlTransient
    public ArrayList<Contact> getContacts() {
        return contact;
    }

 
    public void setContacts(ArrayList<Contact> contacts) {
        this.contact = contacts;
    }

}
