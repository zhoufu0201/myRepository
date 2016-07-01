package adages;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.List;

/**
 * Created by AlexY on 2016/7/1.
 */
public class JsonAllResource extends ServerResource {


    public JsonAllResource() {
    }


    @Get
    public Representation toJson(){

        List<Adage> list = Adages.getList();

//        生成jspn Representation

        JsonRepresentation json = null;

        try {
           json = new JsonRepresentation(new StringRepresentation(list.toString())) ;
        }catch (Exception e){
            e.printStackTrace();
        }


        return json;

    }
}
