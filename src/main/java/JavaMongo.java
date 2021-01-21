import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.Arrays;


public class JavaMongo {

  public static void main(String[] args) throws UnknownHostException {

    MongoClient mongoClient = new MongoClient("100.26.206.112", 27017);

    DB db = mongoClient.getDB("products");

    DBCollection col = db.getCollection("purchases");

    BasicDBObject basicDBObject = new BasicDBObject();
    basicDBObject.put("Sales" , new BasicDBObject("$gte",3000));
    //basicDBObject.put("Profit" , new BasicDBObject("$gte",2000));


    DBCursor result = col.find(basicDBObject);
    while(result.hasNext()){
      System.out.println(result.next());
    }


   // db.purchases.aggregate([{$group:{_id:null, total: {$sum: '$Sales'}}}])


  }
}
