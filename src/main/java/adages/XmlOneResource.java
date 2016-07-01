package adages;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.List;

/**
 * Created by AlexY on 2016/7/1.
 */
public class XmlOneResource extends ServerResource {


    public XmlOneResource() {
    }


    @Get
    public Representation toXml(){


        String sid = (String) getRequest().getAttributes().get("id");

        if ( null == sid){

            return badRequest("No ID provided\n");

        }

        int id;

        try {
            id = Integer.parseInt(sid.trim());

        }catch (Exception e){

            return  badRequest("No such ID\n");
        }


//        搜索这个Adage
        List<Adage> list = Adages.getList();

        Adage adage = Adages.find(id);

        if ( null == adage){
            return badRequest("No adage with ID " + id + "\n");
        }

//        生成xml响应
        DomRepresentation dom = null;

        try {
            dom = new DomRepresentation(MediaType.TEXT_XML);

//            xml文档是否缩进
            dom.setIndenting(true);
            Document doc = dom.getDocument();

//            创建根节点
            Element root = doc.createElement("adage");

            root.appendChild(doc.createTextNode(adage.toString()));
//            将生成的xml放到响应中
            doc.appendChild(root);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return dom;


    }

    private Representation badRequest(String msg) {

        Status error = new Status(Status.CLIENT_ERROR_BAD_REQUEST, msg);
        return new StringRepresentation(error.toString());

    }
}
