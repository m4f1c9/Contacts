package contacts.parser;

import contacts.Contact;
import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Parser {

    public ArrayList<Contact> getContacts(File file) throws JAXBException {
        Wrapper wrapper = new Wrapper();
        
        JAXBContext context = JAXBContext.newInstance(wrapper.getClass());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        wrapper = (Wrapper) unmarshaller.unmarshal(file);

        return wrapper.getContacts();
    }

    public void saveContacts(File file, ArrayList<Contact> con) throws JAXBException {
        Wrapper wrapper = new Wrapper();
        wrapper.setContacts(con);
        
        JAXBContext context = JAXBContext.newInstance(wrapper.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(wrapper, file);
    }
}
