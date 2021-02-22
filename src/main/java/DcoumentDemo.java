import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import java.util.Arrays;
import org.bson.Document;


public class DcoumentDemo {

  public static void main(String[] args) {

    String dBName = "upgrad";
    String connectionString = "mongodb://ec2-3-94-86-35.compute-1.amazonaws.com:27017";
    String collectionName = "students";
    MongoClient mongoClient = null;

    try {
      // Initializing the collection
      mongoClient = MongoClients.create(connectionString);
      MongoDatabase db = mongoClient.getDatabase(dBName);
      MongoCollection<Document> collection = db.getCollection(collectionName);

      // insert Document
      Document doc = new Document("_id", 141)
          .append("name", "Rahul")
          .append("courseId", 114)
          .append("age", 28);
      collection.insertOne(doc);

      // Read Documents
      MongoCursor<Document> cursor = collection.find().cursor();
      while(cursor.hasNext()){
        System.out.println(cursor.next());
      }


      System.out.println("\n\n Printing average age of students in each course");
      cursor = collection.aggregate(
          Arrays.asList(
              Aggregates.group("$courseId",
                  Accumulators.avg("averageAge", "$age"))
          )).cursor();

      while(cursor.hasNext()){
        System.out.println(cursor.next().toJson());

      }
    }
    catch(Exception ex) {
      System.out.println("Got Exception.");
      ex.printStackTrace();
    }
    finally {
      if (null != mongoClient) {
        mongoClient.close();
      }
    }
  }
}
