import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;


/***
 * This class contains few filter operations on ecommerce datasets
 */
public class JavaMongo {

  public static void main(String[] args) {

    System.out.println("Hello Mongo");

    MongoClient mongoClient = MongoClients.create("mongodb://ec2-52-23-240-26.compute-1.amazonaws.com");

    mongoClient.listDatabases().forEach(db -> System.out.println(db.toJson()));

    //Searching for the data

    MongoDatabase mongoDatabase = mongoClient.getDatabase("products");
    MongoCollection<Document> purchaseCollection = mongoDatabase.getCollection("purchases");

    for (Document document : purchaseCollection.find()) {
      System.out.println(document.toJson());
    }

    System.out.println("<--------- Getting one data -----------------> ");

    Document doc = purchaseCollection.find(eq("Order ID", "IN-2014-76016")).first();

    System.out.println(doc);

    System.out.println(" <-------- Filtering the result------------------>");

    for (Document document : purchaseCollection.find(gte("Sales", 3000))) {
      System.out.println(document.toJson());
    }

    System.out.println("<------  Multiple filters -------------------->");

    for (Document document : purchaseCollection.find(and(gte("Sales", 3000), lte("Profit", 2000)))) {
      System.out.println(document.toJson());
    }

    System.out.println("<------ Sorting the data --------->");

    for (Document document : purchaseCollection.find(gte("Sales", 2000)).sort(ascending("Sales"))) {
      System.out.println(document.toJson());
    }
  }
}
