package adages;

import org.restlet.Component;
import org.restlet.data.Protocol;

/**
 * Created by AlexY on 2016/7/1.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // 创建一个ixn的Component
        Component component = new Component();

//        新增一个http server监听8182端口
        component.getServers().add(Protocol.HTTP, 8182);


//        将应用的appilcation类添加进去
//        应用的url就是：
//        http://localhost:8182/adages/xml/1
        component.getDefaultHost().attach("/adages", new AdagesApplication());

        // 启动服务.
        component.start();

    }
}
