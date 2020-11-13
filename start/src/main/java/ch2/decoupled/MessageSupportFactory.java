package ch2.decoupled;
import java.util.Properties;

public class MessageSupportFactory {
    private static MessageSupportFactory instance;

    private Properties properties;
    private MessageProvider provider;
    private MessageRenderer renderer;

    private MessageSupportFactory() {
        properties = new Properties();

        try {
            properties.load(this.getClass().getResourceAsStream("/msf.propirties"));
            String rendererClass = properties.getProperty("renderer.class");
            String providerClass = properties.getProperty("provider.class");

            provider = (MessageProvider) Class.forName(providerClass).newInstance();
            renderer = (MessageRenderer) Class.forName(rendererClass).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        instance = new MessageSupportFactory();
    }

    public static MessageSupportFactory getInstance() {
        return instance;
    }

    public MessageRenderer getMessageRenderer() {
        return renderer;
    }

    public MessageProvider getMessageProvider () {
        return provider;
    }
}
