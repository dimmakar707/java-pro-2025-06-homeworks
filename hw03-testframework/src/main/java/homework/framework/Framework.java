package homework.framework;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Framework {
    private static final Logger logger = LoggerFactory.getLogger(Framework.class);

    public static void run(String className) {

        final List<Method> beforeMethods = new ArrayList<>();
        final List<Method> testedMethods = new ArrayList<>();
        final List<Method> afterMethods = new ArrayList<>();

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
                        logger.info("Exception in @before-method: {}", beforeMethod.getName());
                    }
                }

                if (failBeforeTests > 0) {
                    failTest++;
                } else {
                    try {
                        ReflectionHelper.callMethod(classInstance, method.getName());
                        successTest++;
                    } catch (Exception e) {
                        failTest++;
                        logger.info("Exception in method: {}", method.getName());
                    }
                }

                for (Method afterMethod : afterMethods) {
                    try {
                        ReflectionHelper.callMethod(classInstance, afterMethod.getName());
                    } catch (Exception e) {
                        logger.info("Exception in @after-method: {}", afterMethod.getName());
                    }
                }
            }

            logger.info("-------------------------------");
            logger.info("Test is completed");
            logger.info("Success tests count: {}", successTest);
            logger.info("Failed tests count: {}", failTest);
            logger.info("-------------------------------");

        } catch (Exception e) {
            System.out.println("Exception in framework: " + e.getMessage());
        }
    }
}
