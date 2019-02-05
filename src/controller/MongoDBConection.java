package controller;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.types.ObjectId;


import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

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

        collection.updateOne(eq("number", 2898182), new Document("$set",
                new Document("number", 2987515)));

        System.out.println("End the update of single document");

        System.out.println("Begin the update of multiple documents");

        // Updating multiple documents

        collection.updateMany(eq("number", 2897510),
                combine(set("number", 0), currentDate("lastModified")));

        System.out.println("End the update of multiple documents");

        /*
            Update options

            With updateOne() and updateMany() methods, we can include an UpdateOption to the documents to specify the
            upsert option or bypassDocumentationValidation option.

            ~ upsert                    --> Create a new document in case that document does not matches the
                                            query criteria

            ~ bypassDocumentValidation  --> Allow to make a document validation?
         */

        System.out.println("Begin the update with options");

        collection.updateOne(eq("_id", 1),
                combine(set("name", "Fresh Breads and Tulips"), currentDate("lastModified")),
                new UpdateOptions().upsert(true).bypassDocumentValidation(true));

        System.out.println("End the update with options");

        /**
         * Replace an Existing Document
         *
         * To replace an existing document in a collection we can use a replaceOne method.
         *
         * |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
         * |                         IMPORTANT                                      |
         * |THE _id FIELD IS IMMUTABLE, YOU CAN NOT REPLACE THE _id FIELD VALUE.   |
         * |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
         *
         * @ Filters            --> You can pass in a filter a document to specify which Document to replace.
         *                          To specify a an empty filter you should use an empty Document object.
         *
         *              The replaceOne method replaces a single document even if the filter condition
         *              matches multiple documents in the collection.
         *
         * @ Replace a Document --> To replace a document, pass a new document to the replaceOne method.
         *
         * |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
         * |                        IMPORTANT                                                     |
         * | The replacement document can have different fields from the original document,      |
         * | In the replacement document you can omit the {_id} field because it is inmutable.  |
         * |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
         *
         */

        // The following operation replaces the document whose _id field equal to "5c58e13996823a49deda3ef4"

        System.out.println("Begin the replace of document");

        collection.replaceOne(
                eq("_id", new ObjectId("5c58e13996823a49deda3ef4")),
                new Document("name", "Chris Marin")
                        .append("password", "1234")
                        .append("number", "1234567"));

        System.out.println("End the replace of document");

        /**
         *
         *  Update Options
         *
         *  With the replaceOne method you can include an UpdateOptions documento to specify the upsert option or the
         *  bypassDocumentValidation option.
         *
         */

        // The next operation with replace a document with name "Leo Alvarado Torres" by "Leo A" in this case that
        // document does not exist so it will be created.

        System.out.println("Begin the replace of Document that does not match");

        collection.replaceOne(
                eq("name", "Leo Alvarado Torres"),
                new Document("name", "Leo A")
                        .append("password", "1234")
                        .append("number", "8576849"),
                new UpdateOptions().upsert(true).bypassDocumentValidation(true)
        );

        System.out.println("End of replace of Document with out match");

        /**
         *
         * Delete documents
         *
         * To delete documents we can use deleteOne and deleteMany methods.
         *
         * @ Filters                    --> You can pass in a filter Document to the methods to specify which
         *                                  document to delete.
         *
         * @ Delete a Single Document   --> The deleteOne method deletes a single document, even if the filter matches
         *                                  multiple documents in the collection.
         *
         * @ Delete Multiple Documents  --> The method deleteMany allow to delete all documents that match the filter
         *                                  condition
         */

        System.out.println("Deleting a single document");
        //collection.deleteOne(eq("_id", new ObjectId("5c58e13996823a49deda3ef4")));

        System.out.println("Deleting multiple documents");
        //collection.deleteMany(eq("name", "Leonardo Alvarado"));


    }
}
