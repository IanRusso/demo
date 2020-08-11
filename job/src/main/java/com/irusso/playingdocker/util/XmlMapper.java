package com.irusso.playingdocker.util;

import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class XmlMapper {

    public static <T> T read(String data, Class<T> clazz) throws JAXBException {
        JAXBContext xmlParsingContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = xmlParsingContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new InputSource(new StringReader(data)));
    }
}
