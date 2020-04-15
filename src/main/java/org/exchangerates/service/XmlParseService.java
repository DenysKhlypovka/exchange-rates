package org.exchangerates.service;

import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.net.URL;

public class XmlParseService<T> {
  public T parseFromUrl(String Url, Class<T> parameterClass) {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(parameterClass);
      URL url = new URL(Url);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      return (T) jaxbUnmarshaller.unmarshal(new InputSource(url.openStream()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
