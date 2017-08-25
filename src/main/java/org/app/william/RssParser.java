package org.app.william;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class RssParser {

    private static final String ENTRY = "entry";
    private static final String LINK = "link";
    private static final String TITLE = "title";
    private static final String ATTR_HREF = "href";

    private InputStream stream;
    private List<Entry> entries;

    /**
     *
     * @param is
     */
    RssParser(InputStream is) {
        this.stream = is;
        this.entries = new LinkedList<>();
    }

    /**
     * parses each entry in the rss file for specific tags
     * record information in a list of entities
     */
    void readXML() {
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(stream);
            Entry entry = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String tag = startElement.getName().getLocalPart();
                    switch(tag){
                        case ENTRY: // reset entry for every entry
                            entry = new Entry();
                        case LINK:
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while(attributes.hasNext()){
                                Attribute a = (Attribute) attributes.next();
                                if(a.getName().toString().equals(ATTR_HREF)){
                                    if(entry != null) entry.setUri(a.getValue());
                                }
                            }
                            continue;
                        case TITLE:
                            event = eventReader.nextEvent();
                            if(entry != null) entry.setTitle(event.asCharacters().getData());
                            continue;
                        default:
                            continue;
                    }

                    // parse tags and attributes
                }
                if (event.isEndElement()) {
                    // add item to list
                    EndElement endElement = event.asEndElement();
                    if(endElement.getName().getLocalPart().equals(ENTRY)){
                        entries.add(entry);
                    }
                }
            }

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    void printFeed(){
        for(Entry entry : entries){
            System.out.println(entry);
        }
    }
}
