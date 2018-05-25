import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.Connection;
import org.bson.Document;

//查询年龄⼤于等于30岁，且状态是OK的⽤户
public class work10 {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("192.168.184.131",27017);
            MongoDatabase database = mongoClient.getDatabase("skdb");
            MongoCollection<Document> docs = database.getCollection("persons");
            Connection conn=
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
