package entities;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;


public class Student {

  private int id;
  private String name ;
  private int courseId ;
  private int age ;

  public Student(int id, String name, int courseId, int age) {
    this.id = id;
    this.name = name;
    this.courseId = courseId;
    this.age = age;
  }


  public DBObject createDBObject(){
    BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
    docBuilder.append("_id", this.getId());
    docBuilder.append("name", this.getName());
    docBuilder.append("courseId", this.getCourseId());
    docBuilder.append("age", this.getAge());

    return docBuilder.get();
  }

  @Override
  public String toString() {
    return "Student{" + "id=" + id + ", name='" + name + '\'' + ", courseId=" + courseId + ", age=" + age + '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
