import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class work3 {
    //_id 键默认返回，需要主动指定 _id:0 才会隐藏
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("192.168.184.131", 27017);
            MongoDatabase database = mongoClient.getDatabase("skdb");
            MongoCollection<Document> docs = database.getCollection("persons");
            MongoCursor<Document> res = docs.find().iterator();
            while (res.hasNext()) {

            }
            res.close();
            mongoClient.close();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
        }
    }

