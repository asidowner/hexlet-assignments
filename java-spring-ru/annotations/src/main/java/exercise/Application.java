package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;

import java.lang.reflect.Method;

public class Application {
    private static final String INSPECT_ANNOTATION_TEMPLATE = "Method %s returns a value of type %s.";

    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        for (Method method : Address.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                String name = method.getName();
                String returnTypeName = method.getReturnType().getSimpleName();

                var message = String.format(INSPECT_ANNOTATION_TEMPLATE, name, returnTypeName);

                System.out.println(message);
            }
        }
        // END
    }
}
