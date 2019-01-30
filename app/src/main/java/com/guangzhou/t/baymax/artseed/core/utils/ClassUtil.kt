package com.bjike.t.baymax.artseed.core.utils

import java.lang.reflect.ParameterizedType


object ClassUtil {
  /**
   * 获取类
   * @param o 传入对象
   * @param i 目标对象在<>所在位置索引
   * @return 目标对象
   */
  fun <T> getT(o: Any, i: Int): T? {
    try {
      return ((o.javaClass
              .genericSuperclass as ParameterizedType).actualTypeArguments[i] as Class<T>)
              .newInstance()
    } catch (e: InstantiationException) {
      e.printStackTrace()
    } catch (e: IllegalAccessException) {
      e.printStackTrace()
    } catch (e: ClassCastException) {
      e.printStackTrace()
    }

    return null
  }

  /**
   * 根据类名获取类
   * @param className 类名
   * @return 类对象
   */
  fun forName(className: String): Class<*>? {
    try {
      return Class.forName(className)
    } catch (e: ClassNotFoundException) {
      e.printStackTrace()
    }
    return null
  }
}

