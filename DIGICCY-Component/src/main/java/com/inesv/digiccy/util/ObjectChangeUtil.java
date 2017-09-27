package com.inesv.digiccy.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huguokai on 2016/11/11 0011. 根据实体类转换sql语句
 */
public class ObjectChangeUtil<T> {

	/**
	 * 根据实体类以及表名返回sql语句
	 * 
	 * @param t
	 * @param tableName
	 * @return sql
	 */
	public String objectToSql(T t, String tableName) {
		Field[] fields = t.getClass().getDeclaredFields();
		Method[] methods = t.getClass().getDeclaredMethods();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("insert into ");
		stringBuffer.append(tableName);
		stringBuffer.append("(");
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName() == "id") {
				continue;
			} else {
				stringBuffer.append(fields[i].getName());
				if (i < fields.length - 1) {
					stringBuffer.append(",");
				} else {
					stringBuffer.append(")");
				}
			}
		}
		stringBuffer.append(" values (");
		for (int z = 0; z < fields.length - 1; z++) {
			if (z < fields.length - 2) {
				stringBuffer.append("?,");
			} else {
				stringBuffer.append("?)");
			}
		}
		return stringBuffer.toString();
	}

	/**
	 * 将实体类转成Object数组
	 * 
	 * @param t
	 * @return Object[]
	 */
	public Object[] objectToArray(T t) {
		Field[] fields = t.getClass().getDeclaredFields();
		Method[] methods = t.getClass().getDeclaredMethods();
		List<Object> list = new ArrayList<>();
		for (int j = 0; j < fields.length; j++) {
			for (int i = 0; i < methods.length; i++) {
				try {
					if (methods[i].getName().equalsIgnoreCase("get" + fields[j].getName())) {
						if (fields[j].getName() != "id") {
							//设置vote默认为2  --刘科领注
							if ("getVote".equals(methods[i].getName()) && methods[i].invoke(t) == null) {
								list.add(2);
							} else {
								list.add(methods[i].invoke(t));
							}
							break;
						} else {
							break;
						}
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return list.toArray();
	}

}
