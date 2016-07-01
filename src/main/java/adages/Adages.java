package adages;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by AlexY on 2016/7/1.
 */
public class Adages {


    private static CopyOnWriteArrayList<Adage> adages;
    private static AtomicInteger id;


    static {
        String[] aphorisms = 
                {"What can be shown cannot be said.",
                "If a lion could talk, we could not understand him.",
                "Philosophy is a battle against the bewitchment of our intelligence by means of language.",
                "Ambition is the death of thought.",
                "The limits of my language mean the limits of my world."};
        
        
        adages = new CopyOnWriteArrayList<>();
        id = new AtomicInteger();
        
        for (String str : aphorisms){
            add(str);
        }
        
        
    }
    
    public static String toPlain(){

        // TODO: 2016/7/1 可以用stringbuffer优化 
        String retval = "";
        int i = 1;
        for (Adage adage : adages){
            retval += adage.toString() + "\n";
        }
        
        return retval;
    }
    
    
    public static CopyOnWriteArrayList<Adage> getList(){
        
        return adages;
    }
    
    
    
    public static Adage find(int id){

        Adage adage = null;

        for ( Adage a : adages){
            if ( a.getId() == id){
                adage = a;

                break;
            }

        }

        return adage;

    }
    
    
    

//    支持post请求
    public static void add(String str) {

        int localId = id.incrementAndGet();
        Adage adage = new Adage();

        adage.setWords(str);
        adage.setId(localId);

        adages.add(adage);

    }

}
