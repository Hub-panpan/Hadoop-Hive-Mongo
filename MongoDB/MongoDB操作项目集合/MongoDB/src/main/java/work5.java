import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

//查询年龄⼤于28岁的⽤户
public class work5 {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient=new MongoClient("192.168.184.131",27017);
            MongoDatabase database=mongoClient.getDatabase("skdb");
            MongoCollection<Document> docs=database.getCollection("persons");
            MongoCursor<Document> res=docs.find(Filters.gt("age",28)).iterator();
            while (res.hasNext()){
                Document doc =res.next();
                System.out.println(doc.get("stuname"));
            }
            res.close();
            mongoClient.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
