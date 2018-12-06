package no.ntnu.imt3281.weather;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Forecast {

    public static ArrayList<String> getForecast(String url) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;

        try {
            String base = "/weatherdata/forecast/text/location/time/";
            builder = factory.newDocumentBuilder();
            doc = builder.parse(url);

            XPathFactory xpathFactory = XPathFactory.newInstance();

            XPath xpath = xpathFactory.newXPath();

            XPathExpression expr = xpath.compile(base +"title/text()");

            

        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {

        }

        return null;
    }
}
