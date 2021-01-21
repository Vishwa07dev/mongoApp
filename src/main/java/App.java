import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import entities.Student;
import java.net.UnknownHostException;
import java.util.List;


public class App {

  public static void main(String[] args) throws UnknownHostException {

    MongoClient mongoClient = new MongoClient("54.197.64.163", 27017);
//    List<String> dbs = mongoClient.getDatabaseNames();
//    System.out.println("List of database are : "+dbs);

    /**
     * Write the code to ingest the data into mongodb
     */

    Student student = new Student(1, "Abhinav",356, 28);

    DBObject doc = student.createDBObject();

    DB db = mongoClient.getDB("upgrad");

    DBCollection col = db.getCollection("students");

//    WriteResult result = col.insert(doc);
//    System.out.println(result.toString());

//    /**
//     * Data before update
//     */
//    DBObject query = BasicDBObjectBuilder.start().add("_id", student.getId()).get();
//
//    DBCursor cursor = col.find(query);
//
//    while(cursor.hasNext()){
//      System.out.println("Data before update : "+ cursor.next());
//    }
//
//    /**
//     * Updating the data in Mongo DB
//     */
//    student.setName("Vishwa");
//    doc = student.createDBObject();
//
//    WriteResult result = col.update(query, doc);
//    System.out.println(result.toString());
//
//    /**
//     * Reading the data from Mongo DB
//     */
//    cursor = col.find(query);
//
//    while(cursor.hasNext()){
//      System.out.println("Data after update" + cursor.next());
//    }

    DBObject query = BasicDBObjectBuilder.start().add("_id", student.getId()).get();
    WriteResult result = col.remove(query);
    System.out.println(result.toString());


    //close resources
    mongoClient.close();
  }
}
