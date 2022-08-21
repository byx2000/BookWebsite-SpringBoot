package com.byx.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * JavaBean工具类
 */
public class BeanUtils {
    /**
     * setter方法引用类型
     *
     * @param <T> 对象类型
     * @param <U> 属性类型
     */
    public interface Setter<T, U> extends Serializable {
        void apply(T obj, U value);
    }

    /**
     * getter方法引用类型
     *
     * @param <T> 对象类型
     * @param <U> 属性类型
     */
    public interface Getter<T, U> extends Serializable {
        U apply(T obj);
    }

    private static String getMethodReferenceName(Serializable obj) throws Exception {
        Method method = obj.getClass().getDeclaredMethod("writeReplace");
        method.setAccessible(true);
        SerializedLambda lambda = (SerializedLambda) method.invoke(obj);
        return lambda.getImplMethodName();
    }

    private static String getPropertyName(String getterOrSetterName) {
        String first = getterOrSetterName.substring(3, 4);
        String after = getterOrSetterName.substring(4);
        return first.toLowerCase(Locale.ROOT) + after;
    }

    /**
     * 通过getter方法引用获取属性名
     *
     * @param getter getter方法引用
     * @param <T>    对象类型
     * @param <U>    属性类型
     * @return 属性名
     */
    public static <T, U> String getPropertyName(Getter<T, U> getter) {
        try {
            return getPropertyName(getMethodReferenceName(getter));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据setter方法获取属性名
     *
     * @param setter setter方法引用
     * @param <T>    对象类型
     * @param <U>    属性类型
     * @return 属性名
     */
    public static <T, U> String getPropertyName(Setter<T, U> setter) {
        try {
            return getPropertyName(getMethodReferenceName(setter));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
