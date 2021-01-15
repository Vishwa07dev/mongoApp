import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import entities.Student;
import java.net.UnknownHostException;


public class App {

  public static void main(String[] args) throws UnknownHostException {

    MongoClient mongoClient = new MongoClient("54.174.101.197", 27017);
//    List<String> dbs = mongoClient.getDatabaseNames();
//    System.out.println("List of database are : "+dbs);

    /**
     * Creating/Inserting a new document in the Database
     */
    Student student = new Student(123, "Abhinav", 356, 28);

    DBObject doc = student.createDBObject();

    DB db = mongoClient.getDB("upgrad");

    DBCollection col = db.getCollection("students");

    //create user
    WriteResult result = col.insert(doc);
    System.out.println(result.toString());

    //read example
    DBObject query = BasicDBObjectBuilder.start().add("_id", student.getId()).get();
    DBCursor cursor = col.find(query);
    while (cursor.hasNext()) {
      System.out.println(cursor.next());
    }

    //Updation of the Data
    student.setName("Jyoti");
    doc = student.createDBObject();
    result = col.update(query, doc);
    System.out.println(result.toString());

    //Delete Example
    result = col.remove(query);
    System.out.println(result.toString());

    //close resources
    mongoClient.close();
  }
}
