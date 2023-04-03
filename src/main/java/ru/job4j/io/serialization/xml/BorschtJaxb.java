package ru.job4j.io.serialization.xml;

import ru.job4j.serialization.json.Borscht;
import ru.job4j.serialization.json.Recipe;
import javax.xml.bind.*;
import java.io.StringReader;
import java.io.StringWriter;

public class BorschtJaxb {

    public static void main(String[] args) throws JAXBException {
        String[] ingredients = new String[] {"cabbage", "beetroots", "meat", "sour cream", "dill"};
        final Borscht ukrBorscht = new Borscht(new Recipe("Grandma's recipes"),
                "Ivan", 2, ingredients, true);
        String transitString = convertToXml(ukrBorscht);
        convertFromXml(transitString);
    }

    public static String convertToXml(Borscht borscht) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Borscht.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String rsl = "";

       try (StringWriter writer = new StringWriter()) {
           marshaller.marshal(borscht, writer);
           rsl = writer.getBuffer().toString();
           System.out.println(rsl);
       } catch (Exception e) {
            e.printStackTrace();
        }
       return rsl;
    }

    public static void convertFromXml(String string) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Borscht.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try (StringReader reader = new StringReader(string)) {
               Borscht unmarshalledBorscht = (Borscht) unmarshaller.unmarshal(reader);
            System.out.println(unmarshalledBorscht);
        }
    }
}
