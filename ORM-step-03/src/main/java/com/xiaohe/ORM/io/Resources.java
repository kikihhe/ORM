package com.xiaohe.ORM.io;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @ClassName Resources
 * @Description 资源类
 * @Author 何
 * @Date 2023-05-20 18:46
 * @Version 1.0
 */
public class Resources {
    public static Reader getResourceAsReader(String resource) {
        return new InputStreamReader(getResourceAsStream(resource));
    }

    private static InputStream getResourceAsStream(String resource) {
        ClassLoader[] classLoaders = getClassLoaders();
        for (ClassLoader classLoader : classLoaders) {
            InputStream resourceAsStream = classLoader.getResourceAsStream(resource);
            if (null != resourceAsStream) {
                return resourceAsStream;
            }
        }
        throw new RuntimeException("Could not find resource " + resource);
    }

    /**
     * 获取类加载器
     * @return
     */
    private static ClassLoader[] getClassLoaders() {
        return new ClassLoader[] {
                ClassLoader.getSystemClassLoader(),
                Thread.currentThread().getContextClassLoader()
        };
    }

    public static Class classForName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}
