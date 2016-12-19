package com.focustech.oss2008.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.focustech.common.utils.MessageUtils;
import com.focustech.oss2008.annotation.Display;

/**
 * Generics的util類.
 *
 * @author sshwsfc
 */
@SuppressWarnings("unchecked")
public class GenericsUtils {
    private static final Log log = LogFactory.getLog(GenericsUtils.class);

    private GenericsUtils() {
    }

    /**
     * 通過反射,獲得定義Class時聲明的父類的範型參數的類型. 如public BookManager extends GenricManager<Book>
     *
     * @param clazz The class to introspect
     * @return the first generic declaration, or <code>Object.class</code> if cannot be determined
     */
    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通過反射,獲得定義Class時聲明的父類的範型參數的類型. 如public BookManager extends GenricManager<Book>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or <code>Object.class</code> if cannot be determined
     */
    public static Class getSuperClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            log.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            log.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
                    + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            log.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * 取得當前字段的類型
     *
     * @param clazz 當前類
     * @param fieldName 屬性名稱
     * @return
     */
    public static Class getFieldClass(Object target, String fieldName) throws IllegalArgumentException,
            SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String[] fields = fieldName.split("\\.");
        Object obj = target;
        for (int i = 0; fields != null && i < fields.length - 1; i++) {
            String method = "get".concat(fields[i].substring(0, 1).toUpperCase().concat(fields[i].substring(1)));
            obj = obj.getClass().getMethod(method).invoke(obj);
        }
        if (fields != null && fields.length > 0) {
            fieldName = fields[fields.length - 1];
        }
        fieldName = "get".concat(fieldName.substring(0, 1).toUpperCase().concat(fieldName.substring(1)));
        return obj.getClass().getMethod(fieldName).getReturnType();
    }

    /**
     * 取得當前對象數據域的Display Annotation的值
     *
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws NoSuchFieldException
     */
    public static String getFieldDisplayAnnotation(Object target, String fieldName) throws IllegalArgumentException,
            SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
            NoSuchFieldException {
        Display display;
        String[] fields = fieldName.split("\\.");
        Object obj = target;
        for (int i = 0; fields != null && i < fields.length - 1; i++) {
            // obj = obj.getClass().getDeclaredField(fields[i]);
            String method = "get".concat(fields[i].substring(0, 1).toUpperCase().concat(fields[i].substring(1)));
            obj = obj.getClass().getMethod(method).invoke(obj);
        }
        if (fields != null && fields.length > 0) {
            fieldName = fields[fields.length - 1];
        }
        display = obj.getClass().getDeclaredField(fieldName).getAnnotation(Display.class);
        return display == null ? "" : MessageUtils.getFieldValue(display.value());
    }

    /**
     * 判斷給定父類對象是否是當前類的父類
     *
     * @param target 當前類
     * @param superClass 給定父類
     * @return
     */
    public static boolean isSuperClass(Class target, Class superClass) {
        if (target.getSuperclass() != null) {
            if (target.getSuperclass() == superClass) {
                return true;
            }
            else {
                return isSuperClass(target.getSuperclass(), superClass);
            }
        }
        else
            return false;
    }
}
