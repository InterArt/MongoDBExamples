package am.adik.samples;

import am.adik.samples.model.Address;
import am.adik.samples.model.User;
import am.adik.samples.util.DemoUtils;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

/**
 * Created by Arthur on 6/21/2015.
 */
public class DemoDataGenerator {

    public static void main(String[] args) throws Exception {
        DemoUtils.prepareMongoFactory(); // will clean up MOngoDb
        new DemoDataGenerator().runDefaultAdapter();
//		new MongoDbOutboundAdapterDemo().runAdapterWithConverter();
    }

    public void runDefaultAdapter() throws Exception {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/mongodb-out-config.xml", DemoDataGenerator.class);

        MessageChannel messageChannel = context.getBean("deafultAdapter", MessageChannel.class);
        messageChannel.send(new GenericMessage<User>(this.createPersonA()));
        messageChannel.send(new GenericMessage<User>(this.createPersonB()));
    }

    public void runAdapterWithConverter() throws Exception {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/mongodb-out-config.xml", DemoDataGenerator.class);

        MessageChannel messageChannel = context.getBean("adapterWithConverter", MessageChannel.class);
        messageChannel.send(new GenericMessage<String>("1, John, Dow, 1, Palo Alto, 3401 Hillview Ave"));
    }

    private User createPersonA(){
        Address address = new Address();
        address.setId(1);
        address.setCity("Palo Alto");
        address.setStreet("3401 Hillview Ave");

        User user = new User();
        user.setId(1);
        user.setName("Doe");
        user.setSurname("John");
        user.setAddress(address);

        return user;
    }

    private User createPersonB(){
        Address address = new Address();
        address.setId(2);
        address.setCity("San Francisco");
        address.setStreet("123 Main st");

        User user = new User();
        user.setId(2);
        user.setName("Josh");
        user.setSurname("Doe");
        user.setAddress(address);

        return user;
    }
}
