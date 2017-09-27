package common.autodoc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 工具类
 * 
 * @author dixon
 * @date 2013/10/31
 */
public class Utils
{
    /**
     * 将字符串第一个字母变大写
     * 
     * @param word
     * @return
     */
    public static String upperCaseFirstWord(String word)
    {
        String first = word.substring(0, 1);
        return first.toUpperCase() + word.substring(1);
    }
    
    /**
     * 判断属性是否有get方法
     * 
     * @param c
     * @param f
     * @return
     */
    public static boolean isFieldHaveGetMethod(Class<?> c, Field f)
    {
        try
        {
            c.getDeclaredMethod("get" + Utils.upperCaseFirstWord(f.getName()));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    /**
     * 判断属性是否有get方法
     * 
     * @param c
     * @param f
     * @return
     */
    public static boolean isFieldHaveSetMethod(Class<?> c, Field f)
    {
        try
        {
            c.getDeclaredMethod("set" + Utils.upperCaseFirstWord(f.getName()), f.getType());
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    /**
     * 是否是protected声明
     * 
     * @param f
     * @return
     */
    public static boolean isProtectedField(Field f)
    {
        return Modifier.isProtected(f.getModifiers());
    }
    
}
