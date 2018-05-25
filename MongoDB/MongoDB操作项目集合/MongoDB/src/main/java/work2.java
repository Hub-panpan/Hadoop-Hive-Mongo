import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

//查询全部⽂档，并只显示name字段
public class work2 {
    public static void main(String[] args) {
        try{
            MongoClient mongoClient=new MongoClient("192.168.184.131",27017);
            MongoDatabase database=mongoClient.getDatabase("skdb");
            System.out.println("数据库连接成功！");
            MongoCollection<Document> docs=database.getCollection("persons");
            MongoCursor<Document> res =docs.find().iterator();
            while (res.hasNext())
            {
                Document doc=res.next();
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
