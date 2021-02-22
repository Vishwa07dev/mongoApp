import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import java.util.Arrays;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;


/***
 * This class contains few filter operations on ecommerce datasets
 */
public class JavaMongo {

  public static void main(String[] args) {

    System.out.println("Hello Mongo");

    MongoClient mongoClient = MongoClients.create("mongodb://ec2-3-94-86-35.compute-1.amazonaws.com");

    mongoClient.listDatabases().forEach(db -> System.out.println(db.toJson()));

    //Searching for the data

    MongoDatabase mongoDatabase = mongoClient.getDatabase("products");
    MongoCollection<Document> purchaseCollection = mongoDatabase.getCollection("purchases");

    for (Document document : purchaseCollection.find()) {
      System.out.println(document.toJson());
    }
    System.out.println("<--------- Printing the first three documents LIMIT -----------------> ");

    for (Document document : purchaseCollection.find().limit(3)) {
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

    System.out.println("<------  Finding the count-------------------->");
    System.out.println(purchaseCollection.countDocuments(lte("Profit", 200)));

    System.out.println("<------  Filtering based on the segments  -------------------->");

    for (Document document : purchaseCollection.find(or(eq("Segment", "Consumer"), eq("Segment", "Corporate")))) {
      System.out.println(document.toJson());
    }

    System.out.println("<------ Sorting the data --------->");

    for (Document document : purchaseCollection.find(gte("Sales", 2000)).sort(ascending("Sales"))) {
      System.out.println(document.toJson());
    }

    System.out.println("<------ Aggregation queries --------->");


    System.out.println("<------ Total sales aggregated based on the segment --------->");
    for(Document document : purchaseCollection.aggregate(Arrays.asList(
        Aggregates.group("$Segment",
            Accumulators.sum("totalSales", "$Sales"))
    ))){
      System.out.println(document.toJson());
    }

    System.out.println("<------ Total sales across all the orders --------->");
    for(Document document : purchaseCollection.aggregate(Arrays.asList(
        Aggregates.group(null,
            Accumulators.sum("totalSalesAcrossAllOrders", "$Sales"))
    ))){
      System.out.println(document.toJson());
    }


    System.out.println("<------ Average sales across all the orders --------->");
    for(Document document : purchaseCollection.aggregate(Arrays.asList(
        Aggregates.group(null,
            Accumulators.avg("averageSalesAcrossAllOrders", "$Sales"))
    ))){
      System.out.println(document.toJson());
    }




    /**
     *  // insert Document
     *       Document doc = new Document("_id", 141)
     *           .append("name", "Rahul")
     *           .append("courseId", 114)
     *           .append("age", 28);
     *       collection.insertOne(doc);
     *
     *       // Read Documents
     *       MongoCursor<Document> cursor = collection.find().cursor();
     *       while(cursor.hasNext()){
     *         System.out.println(cursor.next());
     *       }
     *
     *
     *       System.out.println("\n\n Printing average age of students in each course");
     *       cursor = collection.aggregate(
     *           Arrays.asList(
     *               Aggregates.group("$courseId",
     *                   Accumulators.avg("averageAge", "$age"))
     *           )).cursor();
     *
     *       while(cursor.hasNext()){
     *         System.out.println(cursor.next().toJson());
     *
     *       }
     *     }
     */


  }
}
