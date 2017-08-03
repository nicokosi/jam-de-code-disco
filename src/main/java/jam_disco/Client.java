package jam_disco;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Client {

   public static void main(String[] args) throws UnirestException {
      System.out.println(
            Unirest.get("http://localhost:8080/")
                  .asString()
                  .getBody()
      );
   }
}
