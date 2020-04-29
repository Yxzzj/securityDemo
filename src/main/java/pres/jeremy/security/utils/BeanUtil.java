package pres.jeremy.security.utils;

import java.util.ArrayList;
import java.util.List;

public class BeanUtil extends cn.hutool.core.bean.BeanUtil {

    /**
     * Clone t.
     *
     * @param <T>            the type parameter
     * @param sourceObj      the source obj
     * @param targetObjClass the target obj class
     * @return the t
     * @throws IllegalAccessException the illegal access exception
     * @throws InstantiationException the instantiation exception
     */
    public static <T> T clone(Object sourceObj, Class<T> targetObjClass) {
        T t = null;
        try {
            t = targetObjClass.newInstance();
            copyProperties(sourceObj, t);
        } catch (Exception e) {
        }
        return t;
    }

    /**
     * Clone t[].
     *
     * @param <T>            the type parameter
     * @param objects      the source obj
     * @param targetObjClass the target obj class
     * @return the t
     * @throws IllegalAccessException the illegal access exception
     * @throws InstantiationException the instantiation exception
     */
    public static <T> T cloneMore(Object[] objects, Class<T> targetObjClass) {
        T t = null;
        try {
            t = targetObjClass.newInstance();
            for (int i = 0; i < objects.length; i++) {
                copyProperties(objects[i], t);
            }
        } catch (Exception ignored) {
        }
        return t;
    }

    /**
     * List clone list.
     *
     * @param <T>            the type parameter
     * @param sourceList     the source list
     * @param targetObjClass the target obj class
     * @return the list
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     */
    public static <T> List<T> listClone(List sourceList, Class<T> targetObjClass){
        List<T> resultList = new ArrayList<>(16);
        if(sourceList!=null){
            for (Object o : sourceList) {
                T t = clone(o,targetObjClass);
                if (t != null) {
                    resultList.add(t);
                }
            }
        }
        return resultList;
    }

}
