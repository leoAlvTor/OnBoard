package controlador;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import java.util.Arrays;

public class MongoDBConection {

    //Conexion con MongoDB host local y direccion 27017
    MongoClient mongoClient = new MongoClient("localhost", 27017);

    // Conexion a la base de datos AlvaradoStore
    MongoDatabase database = mongoClient.getDatabase("AlvaradoStore");

    // Obtencion de la coleccion (Su analogo en SQL seria tabla) Users.
    MongoCollection<Document> col1 = database.getCollection("Users");


}
