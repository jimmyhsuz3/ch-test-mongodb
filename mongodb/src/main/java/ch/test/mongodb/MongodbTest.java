package ch.test.mongodb;
import java.util.Arrays;
import java.util.Iterator;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
public class MongodbTest {
	private String userName = "mdb0c00038_10400dss";
	private String database = "mdb0c00038";
	private String password = "mdb0c00038_10400dss";
	public static void main(String[] args){
		System.out.println(new MongodbTest().testMongodb());
	}
    public String testMongodb() {
    	StringBuilder builder = new StringBuilder();
    	MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());
    	MongoClient mongoClient = null;
    	try {
    		mongoClient = new MongoClient(new ServerAddress("172.19.0.160", 22004), Arrays.asList(credential));
    		MongoDatabase db = mongoClient.getDatabase("mdb0c00038");
    		MongoCollection<Document> coll = db.getCollection("jimmy_test");
    		for (Iterator<Document> iter = coll.find().iterator(); iter.hasNext();)
    			builder.append(iter.next().toString()).append('\n');
    	} catch (RuntimeException re){
    		re.printStackTrace();
    		builder.append(re.getLocalizedMessage());
    	} finally {
    		if (mongoClient != null)
    			mongoClient.close();
    	}
    	return builder.toString();
    }
}