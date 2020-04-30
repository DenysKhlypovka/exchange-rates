package org.exchangerates.xml;

import org.exchangerates.xml.adapter.DateAdapterDash;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import java.net.URL;

public class XmlParser {
  public static <T> Object parseFromUrl(String Url, Class<T> parameterClass) {
    try {
      var jaxbContext = JAXBContext.newInstance(parameterClass);
      var url = new URL(Url);
      var jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      jaxbUnmarshaller.setAdapter(DateAdapterDash.class, new DateAdapterDash());
      return parameterClass.cast(jaxbUnmarshaller.unmarshal(new InputSource(url.openStream())));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
