import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class work1 {
    //p23 查询全部⽂档，并显示所有字段
    public static void main(String[] args) {
        try{
            //1、连接MongoDB
            MongoClient mongoClient=new MongoClient("192.168.184.131",27017);
            //2、连接数据库
            MongoDatabase database=mongoClient.getDatabase("skdb");
            System.out.println("连接数据库成功！");
           //3、获得集合
            MongoCollection<Document> docs=database.getCollection("persons");
            MongoCursor<Document> res=docs.find().iterator();
            while(res.hasNext()) {
                Document doc =res.next();
                System.out.println(doc);
            }
            res.close();
            mongoClient.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}
