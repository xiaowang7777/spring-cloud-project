package com.wjf.github.commons.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanConvertUtil {

	private static <E, F> E getEByF(F f, Class<E> clazz) throws IllegalAccessException, InstantiationException {
		final E e = clazz.newInstance();
		final Class<?> fClazz = f.getClass();

		final Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			try {
				final Field declaredField = fClazz.getDeclaredField(field.getName());
				if (declaredField == null || declaredField.getType() != field.getType()) {
					continue;
				}
				final String fieldName = declaredField.getName();
				final String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				final String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				final Method setMethod = clazz.getMethod(setMethodName, field.getType());
				final Method getMethod = fClazz.getMethod(getMethodName);
				setMethod.invoke(e, getMethod.invoke(f));
			} catch (NoSuchMethodException | InvocationTargetException | NoSuchFieldException | IllegalAccessException ex) {
				ex.fillInStackTrace();
			}
		}
		return e;
	}

}
