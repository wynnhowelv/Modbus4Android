package com.zgkxzx.modbus4And.logging;


public class LogFactory {
    public static <T> Log getLog(Class<T> tClass) {
        return new Log(tClass);
    }
}
