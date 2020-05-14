package org.exchangerates.xml;

import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import java.net.URL;

public class XmlParser {
  public static <T> Object parseFromUrl(String urlStr, Class<T> parameterClass) {
    try {
      var jaxbContext = JAXBContext.newInstance(parameterClass);
      var url = new URL(urlStr);
      var jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      return parameterClass.cast(jaxbUnmarshaller.unmarshal(new InputSource(url.openStream())));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
