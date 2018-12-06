package no.ntnu.imt3281.weather;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
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
            ArrayList<String> forecast = new ArrayList<>();
            List<String> title = new ArrayList<>();
            List<String> body = new ArrayList<>();
            String base = "/weatherdata/forecast/text/location/time/";
            builder = factory.newDocumentBuilder();
            doc = builder.parse(url);

            XPathFactory xpathFactory = XPathFactory.newInstance();

            XPath xpath = xpathFactory.newXPath();

            XPathExpression expr = xpath.compile(base +"title/text()");

           NodeList titles = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

           for(int i = 0; i < titles.getLength();i++) {
               title.add(titles.item(i).getNodeValue());
           }

            expr = xpath.compile(base +"body/text()");

            NodeList bodies = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for(int i = 0; i < titles.getLength();i++) {
                body.add(bodies.item(i).getNodeValue());
            }

            for (int i = 0; i <titles.getLength(); i++) {
                forecast.add(title.get(i));
                forecast.add(body.get(i));
            }

            return forecast;


        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {

        }

        return null;
    }
}
