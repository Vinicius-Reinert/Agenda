package util;

/**
 * Created by Vinicius Sipoli Reinert.
 */
public class RESTUtils {

    public static boolean isInteger(String s){
        try{
            Long.parseLong(s);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
}
