package ch2.decoupled;

public class HelloWorldFactory {
    public static void main(String[] args) {
        MessageProvider messageProvider = MessageSupportFactory.getInstance().getMessageProvider();
        MessageRenderer messageRenderer = MessageSupportFactory.getInstance().getMessageRenderer();
        messageRenderer.setMessageProvider(messageProvider);
        messageRenderer.render();
    }
}
