package com.zgkxzx.modbus4And.logging;

public class Log {
    private String tag;
    public <T> Log(Class<T> tClass) {
        tag = tClass.getSimpleName();
    }

    public boolean isDebugEnabled() {
        return true;
    }

    public void debug(String s) {
        android.util.Log.d(tag, s);
    }

    public void debug(String s, Throwable e) {
        android.util.Log.d(tag, s, e);
    }

    public void warn(String s) {
        android.util.Log.w(tag, s);
    }

    public void warn(String s, Throwable e) {
        android.util.Log.w(tag, s, e);
    }

    public void info(String s) {
        android.util.Log.i(tag, s);
    }

    public void error(String message, Exception e) {
        android.util.Log.e(tag, message, e);
    }
}
