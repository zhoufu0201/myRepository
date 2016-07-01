package adages;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;


/**
 * Created by AlexY on 2016/7/1.
 */
public class PlainResource extends ServerResource {


    public PlainResource() {
    }


    @Get
    public Representation toPlain(){

        String adages = Adages.toPlain();

        setStatus(Status.SUCCESS_OK);

//        返回纯文件的响应内容
        return new StringRepresentation(adages, MediaType.TEXT_PLAIN);


    }
}
