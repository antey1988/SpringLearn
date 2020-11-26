package ch2.decoupled;

public class HelloWorldInterfaces {
    public static void main(String[] args) {
        MessageProvider messageProvider = new HelloWorldMessageProvider();
        MessageRenderer messageRenderer = new StandardOutMessageRenderer();
        messageRenderer.setMessageProvider(messageProvider);
        messageRenderer.render();
    }
}
