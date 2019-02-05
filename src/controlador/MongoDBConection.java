package controlador;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import java.util.Arrays;

import static com.mongodb.client.model.Filters.*;

public class MongoDBConection {

    public MongoDBConection(){
        // Create a new connection with LocalHost and it's port.
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Connect to AlvaradoStore's Database
        MongoDatabase database = mongoClient.getDatabase("AlvaradoStore");

        // Get a collection (Collection is analog to Table in SQL databases), in this case Users
        MongoCollection<Document> collection = database.getCollection("Users");

        // Get the first data in my collection of Users
        Document myDoc = collection.find().first();

        // Print the data acquired before

        System.out.println(myDoc.toJson());


        // Retrieve all documents in the collection and then print one by one
        System.out.println("Begin the print of all documents");
        MongoCursor<Document> cursor = collection.find().iterator();
        try{
            while(cursor.hasNext()){
                System.out.println(cursor.next().toJson());
            }
        }finally {
            cursor.close();
        }

        /**
         *  Specifying a Query Filter
         *
         *  It let to query documents that match certain conditions.
         */

        System.out.println("Begin the print of Equal filter");

        // Get a single document that matches a filter
        myDoc = collection.find(eq("name", "Leonardo Alvarado")).first();

        System.out.println(myDoc.toJson());

        System.out.println("Begin the print of Logical match");

        // Return and print all documents
        Block<Document> printBlock = document -> System.out.println(document.toJson());

        System.out.println("Print a number greater than another one");

        // Get a number greater than 3287584
        collection.find(gte("number", 3287584)).forEach(printBlock);


        System.out.println("Print a number in a specify range");

        //Get certain values in a range, such as 10<i<=20 using the AND helper
        collection.find(and(gt("number", 2898182),
                lte("number", 3287584))).forEach(printBlock);


        /**
         *  Updating information in Documents
         *
         *  Steps to update collection Document
         *
         *  1. Filter document or documents to update using an empty Document object.
         *
         *  2. Create an update document that with the modifications.
         *
         *  ~~ UPDATE OPERATORS ~~
         *
         *  All update operator start with $ symbol
         *
         *  $currentDate --> Sets the value of a field to current date, either as Date or Timestamp.
         *  $inc         --> Increments the value of the field by a specified amount.
         *  $min         --> Update the field if the specified value is less than the existing field value.
         *  $max         --> Update the field if the specified value is greater than the existing field value.
         *  $mul         --> Multiplies the value of the field by the specified amount.
         *  $rename      --> Rename a field.
         *  $set         --> Sets a value of a field in a Documents.
         *  $setOnInsert --> Sets a value of a field if an Update results in an insert of a document.
         *  $unset       --> Removes the specified field from a document.
         *
         *  The update methods return an UpdateResult which provide information about the update and the amount of
         *  documents modified.
         */

        System.out.println("Begin the update");

        // Updating a single Document using updateOne

        collection.updateOne(eq("number", 2898182), new Document("$set", new Document("i", 110)));

    }
}
