package ch4.jsr330;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named("messageRenderer")
@Singleton
public class StandardOutMessageRenderer implements MessageRenderer{
    @Inject
    @Named("messageProvider")
    private MessageProvider messageProvider = null;

    @Override
    public void render() {
        if (messageProvider == null)
            throw new RuntimeException("You must set the property messageProvider of class:"
                    + StandardOutMessageRenderer.class.getName());
        System.out.println(messageProvider.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}
