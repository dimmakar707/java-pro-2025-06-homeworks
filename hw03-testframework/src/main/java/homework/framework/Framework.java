package homework.framework;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Framework {
    private static final List<Method> beforeMethods = new ArrayList<>();
    private static final List<Method> testedMethods = new ArrayList<>();
    private static final List<Method> afterMethods = new ArrayList<>();

    public static void run(String className) {

        try {
            Class<?> testClass = Class.forName(className);
            int successTest = 0;
            int failTest = 0;

            for (Method method : testClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Before.class)) {
                    beforeMethods.add(method);
                }
                if (method.isAnnotationPresent(Test.class)) {
                    testedMethods.add(method);
                }
                if (method.isAnnotationPresent(After.class)) {
                    afterMethods.add(method);
                }
            }

            for (Method method : testedMethods) {

                Object classInstance = ReflectionHelper.instantiate(testClass);
                int failBeforeTests = 0;

                for (Method beforeMethod : beforeMethods) {
                    try {
                        ReflectionHelper.callMethod(classInstance, beforeMethod.getName());
                    } catch (Exception e) {
                        failBeforeTests++;
                        System.out.println("Exception in @before-method: " + beforeMethod.getName());
                    }
                }

                if (failBeforeTests > 0) {
                    failTest++;
                    continue;
                }

                try {
                    ReflectionHelper.callMethod(classInstance, method.getName());
                    successTest++;
                } catch (Exception e) {
                    failTest++;
                    System.out.println("Exception in method: " + method.getName());
                }

                for (Method afterMethod : afterMethods) {
                    try {
                        ReflectionHelper.callMethod(classInstance, afterMethod.getName());
                    } catch (Exception e) {
                        System.out.println("Exception in @after-method: " + afterMethod.getName());
                    }
                }
            }

            System.out.println("-------------------------------");
            System.out.println("Test is completed");
            System.out.println("Success tests count: " + successTest);
            System.out.println("Failed tests count: " + failTest);
            System.out.println("-------------------------------");

        } catch (Exception e) {
            System.out.println("Exception in framework: " + e.getMessage());
        }
    }
}
