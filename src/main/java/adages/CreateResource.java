package adages;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;


/**
 * Created by AlexY on 2016/7/1.
 */
public class CreateResource extends ServerResource {


    public CreateResource() {
    }



    @Post
    public Representation create(Representation data){
        Status status  = null;

        String msg  = null;

//        从post的body中提取数据
        Form form = new Form(data);
        String words = form.getFirstValue("words");


        if ( null == words){
            msg = "No words were given for the adage.\n";
            status = Status.CLIENT_ERROR_BAD_REQUEST;

        }else{
            Adages.add(words);

            msg = "The adage '" + words + "' has been added.\n";
            status = Status.SUCCESS_OK;


        }

        setStatus(status);
        return new StringRepresentation(msg, MediaType.TEXT_PLAIN);

    }
}
