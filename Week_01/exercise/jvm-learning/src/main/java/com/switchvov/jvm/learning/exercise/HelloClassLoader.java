package com.switchvov.jvm.learning.exercise;

import java.io.InputStream;

/**
 * @author switch
 * @since 2021/1/14
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        Object hello = helloClassLoader.findClass("Hello").newInstance();
        hello.getClass().getMethod("hello").invoke(hello);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String filename = name + ".xlass";
        try {
            InputStream stream = getResourceAsStream(filename);
            byte[] bytes = new byte[1024];
            int len = stream.read(bytes);
            for (int i = 0; i < len; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            return defineClass(name, bytes, 0, len);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
