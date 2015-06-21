package am.adik.samples.util;

import com.mongodb.MongoClient;

import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by Arthur on 6/21/2015.
 */
public class DemoUtils {

    public static MongoDbFactory prepareMongoFactory(String... additionalCollectionToDrop) throws Exception{
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new MongoClient(), "test");
        MongoTemplate template = new MongoTemplate(mongoDbFactory);
        template.dropCollection("messages");
        template.dropCollection("data");
        for (String additionalCollection : additionalCollectionToDrop) {
            template.dropCollection(additionalCollection);
        }
        return mongoDbFactory;
    }

}
