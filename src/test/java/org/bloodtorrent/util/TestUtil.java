package org.bloodtorrent.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 29
 * Time: 오후 1:33
 * To change this template use File | Settings | File Templates.
 */
public class TestUtil {

    public static <T> void setDummyString(T t, String property, int num) {
        String dummyText = makeDummyString(num);
        invokeMethod(t, property, dummyText);
    }

    public static <T> void setDummyNumericString(T t, String property, int num) {
        String dummyNumericText = makeDummyNumericString(num);
        invokeMethod(t, property, dummyNumericText);
    }

    public static <T> void invokeMethod(T t, String property, String dummyText) {
        for (Method method : t.getClass().getMethods()) {
            String methodName = method.getName();
            if(methodName.startsWith("set") && methodName.toLowerCase().endsWith(property.toLowerCase())) {
                try {
                    method.invoke(t, dummyText);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String makeDummyString(int num, String dummyChar) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < num ; i++){
            stringBuilder.append(dummyChar);
        }
        return stringBuilder.toString();
    }

    public static String makeDummyString(int num) {
        return makeDummyString(num, "*");
    }

    public static String makeDummyNumericString(int num) {
        return makeDummyString(num, "1");
    }
}
