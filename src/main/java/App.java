import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entities.Student;
import java.util.ArrayList;
import org.bson.Document;
import java.util.List;
import org.bson.conversions.Bson;


import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;


/**
 * This class contains the code for the CRUD operations
 *
 */
public class App {

  public static void main(String[] args) {

    MongoClient mongoClient = MongoClients.create("mongodb://ec2-3-94-86-35.compute-1.amazonaws.com");
    System.out.println("<------ Inserting a record --------->");
    MongoDatabase sampleTrainingDB = mongoClient.getDatabase("upgrad");
    MongoCollection<Document> studentsCollection = sampleTrainingDB.getCollection("students");

    Student student = new Student(1, "Abhinav", 356, 28);
    System.out.println(studentsCollection.insertOne(student.createDBObject()));

    System.out.println("<!----- Bulk insert ----------->");
    List<Document> documentList = getDocuments();
    System.out.println(studentsCollection.insertMany(documentList));

    System.out.println("<! ------ Reading the data ---->");
    for (Document document : studentsCollection.find()) {
      System.out.println(document.toJson());
    }

    System.out.println("< --- Reading one record -------->");
    Bson filter = eq("name", "Akash");
    System.out.println((studentsCollection.find(filter)).first().toJson());

    System.out.println("< --- Updating the record -----> ");
    Bson updateOperation = set("age", 1111);
    System.out.println(studentsCollection.updateOne(filter, updateOperation));
    System.out.println((studentsCollection.find(filter)).first().toJson());

    System.out.println("<! ---- Deleting the records in the db ---------> ");
    System.out.println(studentsCollection.deleteOne(eq("name", "Abhinav")));

    System.out.println("<! ---- Deleting the entire collection in the db ---------> ");
    studentsCollection.drop();
  }

  private static List<Document> getDocuments() {
    Student student1 = new Student(1, "Vishwa", 356, 99);
    Student student2 = new Student(1, "Mohan", 205, 102);
    Student student3 = new Student(1, "Akash", 179, 77);
    Student student4 = new Student(1, "Akansha", 258, 55);
    Student student5 = new Student(1, "Shivani", 301, 43);

    List<Document> documentList = new ArrayList<>();
    documentList.add(student1.createDBObject());
    documentList.add(student2.createDBObject());
    documentList.add(student3.createDBObject());
    documentList.add(student4.createDBObject());
    documentList.add(student5.createDBObject());
    return documentList;
  }
}
