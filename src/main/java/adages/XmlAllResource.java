package adages;

import org.restlet.data.MediaType;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.List;

/**
 * Created by AlexY on 2016/7/1.
 */
public class XmlAllResource extends ServerResource {

    public XmlAllResource() {
    }


    @Get
    public Representation toXml(){

        List<Adage> list = Adages.getList();


        DomRepresentation dom = null;


        try {
            dom = new DomRepresentation(MediaType.TEXT_XML);

            dom.setIndenting(true);

            Document doc = dom.getDocument();

            Element root = doc.createElement("adages");

            for ( Adage adage : list){

                Element next = doc.createElement("adage");
                next.appendChild(doc.createTextNode(adage.toString()));

                root.appendChild(next);


            }


            doc.appendChild(root);



        } catch (IOException e) {
            e.printStackTrace();
        }



        return dom;


    }
}
