package adages;

import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.Status;
import org.restlet.routing.Router;

/**
 * Created by AlexY on 2016/7/1.
 */
public class AdagesApplication extends Application {


    @Override
    public synchronized Restlet createInboundRoot() {


//        通过一个匿名类来实现了DELETE操作，其他的操作通过独立的java类实现


//        DELETE处理
        Restlet janitor = new Restlet(getContext()) {

            @Override
            public void handle(Request request, Response response) {

                String msg = null;

                String sid = (String ) request.getAttributes().get("id");

                if ( null == sid){
                    msg = badRequest("No ID given.\n");
                }

                Integer id = null;

                try {
                    id = Integer.parseInt(sid.trim());

                }  catch (Exception e){
                    msg = badRequest("Ill-formed ID.\n");

                }


                Adage adage = Adages.find(id);

                if ( null == adage){

                    msg = badRequest("No adage with ID " + id + "\n");

                }else {
                    Adages.getList().remove(adage);
                    msg = "Adage " + id + " removed.\n";

                }



                response.setEntity(msg, org.restlet.data.MediaType.TEXT_PLAIN);



            }

        };

//        创建路由表
        Router router = new Router(getContext());
        router.attach("/", PlainResource.class);
        router.attach("/xml",         XmlAllResource.class);
        router.attach("/xml/{id}",    XmlOneResource.class);
        router.attach("/json",        JsonAllResource.class);
        router.attach("/create",      CreateResource.class);
        router.attach("/update",      UpdateResource.class);


        return router;




    }




    private String badRequest(String msg) {

        Status error = new Status(Status.CLIENT_ERROR_BAD_REQUEST,msg);

        return error.toString();

    }
}
