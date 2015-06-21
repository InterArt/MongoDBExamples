package am.adik.samples.util;


import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;
import org.springframework.util.StringUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Created by Arthur on 6/21/2015.
 */
public class StringConverter extends MappingMongoConverter {

    public StringConverter(
            MongoDbFactory mongoDbFactory,
            MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext) {
        super(new DefaultDbRefResolver(mongoDbFactory), mappingContext);
    }

    @Override
    public void write(Object source, DBObject target) {
        String strPerson = (String) source;
        String[] parsedStrPerson = StringUtils.tokenizeToStringArray(strPerson, ",");
        target.put("id", parsedStrPerson[0]);
        target.put("name", parsedStrPerson[1]);
        target.put("surname", parsedStrPerson[2]);
        DBObject innerObject = new BasicDBObject();
        innerObject.put("id", parsedStrPerson[3]);
        innerObject.put("city", parsedStrPerson[4]);
        innerObject.put("street", parsedStrPerson[5]);
        target.put("address", innerObject);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <S> S read(Class<S> clazz, DBObject source) {
        return (S) ((source.get("id") + ", ")
                + source.get("name") + ", "
                + source.get("surname") + ", "
                + source.get("id") + ", "
                + source.get("city") + ", "
                + source.get("street") + ", ");
    }

}
