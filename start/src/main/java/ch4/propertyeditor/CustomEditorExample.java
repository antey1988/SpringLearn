package ch4.propertyeditor;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CustomEditorExample {
    private FullName fullName;

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("/spring/propertyeditor/app-config-02.xml");
        ctx.refresh();

        CustomEditorExample customEditorExample = (CustomEditorExample) ctx.getBean("exampleBean");
        System.out.println(customEditorExample.getFullName());
        ctx.close();
    }
}
