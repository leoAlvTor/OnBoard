package controller;

import com.mongodb.*;

public class MongoConnection {

    private Mongo mongo;
    private DB db;
    private DBCollection collectionUsers;

    public MongoConnection(){
        connectDataBase();
    }

    public void connectDataBase(){
        mongo = new Mongo();

        db = mongo.getDB("AlvaradoStore");
        collectionUsers = db.getCollection("Users");

    }

    public void createUsers(){
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("name", "220V");
        dbObject.put("password", "1234");
        collectionUsers.insert(dbObject);
    }

    public DBCursor retriveUsers(){
        BasicDBObject query = new BasicDBObject();
        DBCursor cursor = collectionUsers.find();
        return cursor;
    }

    public boolean checkUsers(String name, String password){
        System.out.println("name: " + name + "password:" + password);

        boolean flag = false;
        DBCursor users = retriveUsers();

        DBObject objectUser;
        while(users.hasNext()) {
            objectUser = users.next();
            if(objectUser.get("name").equals(name) && objectUser.get("password").equals(password))
                flag = true;
        }

        return flag;
    }
}
