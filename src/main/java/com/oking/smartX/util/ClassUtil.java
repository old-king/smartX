package com.oking.smartX.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * @author 谢青
 * @Description: 加载Class配置信息工具类
 * @date 2017/4/19 0019 15:08
 * ${tags}
 */
public class ClassUtil {
    /**
     * 包目录分隔符:点
     */
    public static final String PACKAGE_PATH_POINT = ".";
    /**
     * 包目录分隔符:点
     */
    public static final String PACKAGE_PATH_SEPARATOR = "/";
    /**
     * URL 协议名:file
     */
    public static final String URL_PROTOCOL_FILE = "file";
    /**
     * URL 协议名:jar
     */
    public static final String URL_PROTOCOL_JAR = "jar";
    /**
     * class 文件名后缀
     */
    public static final String CLASS_FILE_END = ".class";
    /**
     * jar 文件名后缀
     */
    public static final String JAR_FILE_END = ".jar";
    /**
     * jar 文件名后缀
     */
    public static final String ZIP_FILE_END = ".zip";
    /**
     * class 文件分隔符:点
     */
    public static final String CLASS_FILE_POINT = PACKAGE_PATH_POINT;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);
    /**
     * 当前线程的类加载
     */
    private static final ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();

    public static ClassLoader getCurrentClassLoader() {
        return currentClassLoader;
    }

    /**
     * 根据包名和注解,获取包下的有该注解的类列表
     *
     * @param packageName     包名
     * @param annotationClass 注解
     * @return
     */
    public static List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        List<Class<?>> classList = getClassList(packageName);
        return classList.stream().filter(clazz ->
                clazz.isAnnotationPresent(annotationClass)).collect(Collectors.toList());
    }

    public static List<Class<?>> getClassList(String packageName) {
        List<Class<?>> classList = new ArrayList<>();

        //由包路径转换为地址路径，将"."变为"/"
        String packageUrl = packageName.replace(PACKAGE_PATH_POINT, PACKAGE_PATH_SEPARATOR);
        try {
            Enumeration<URL> urlList = getCurrentClassLoader().getResources(packageUrl);
            while (urlList.hasMoreElements()) {
                URL url = urlList.nextElement();
                if (url != null) {
                    //获取url的 协议名  [file] [jar]
                    String protocol = url.getProtocol();
                    String packagePath = url.getPath();
                    //协议名为file
                    if (URL_PROTOCOL_FILE.equals(protocol)) {
                        loadClass(classList, packagePath, packageName);
                    } else if (URL_PROTOCOL_JAR.endsWith(protocol)) {
                        loadJarClass(classList, packagePath);
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("  The smartX Framework  load configuration ERROR !");
        }

        return classList;
    }

    private static void loadClass(List<Class<?>> classList, String packagePath, String packageName) {
        //获取 包路径 下的所有class文件或者目录
        File[] files = new File(packagePath).listFiles(file -> file.isFile() && file.getName().endsWith(CLASS_FILE_END) || file.isDirectory());

        //遍历文件或者目录
        for (File file : files) {
            String fileName = file.getName();
            //文件操作
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf(CLASS_FILE_POINT));
                if (StringUtils.isNotEmpty(className)) {
                    className = packageName + PACKAGE_PATH_POINT + className;
                }
                //加载class文件
                Class<?> clz = loadClass(className);
                //存入Class集合
                classList.add(clz);
            } else {
                //子文件路径
                String subPackagePath = fileName;
                if (StringUtils.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + PACKAGE_PATH_SEPARATOR + subPackagePath;
                }
                //子包名
                String subPackageName = fileName;
                if (StringUtils.isNotEmpty(packageName)) {
                    subPackageName = packageName + PACKAGE_PATH_POINT + subPackageName;
                }

                //递归调用，遍历文件存入class集合
                loadClass(classList, subPackagePath, subPackageName);
            }
        }
    }

    private static void loadJarClass(List<Class<?>> classes, String jarPath) {
        // 定义一个枚举的集合 并进行循环来处理这个目录下的URL
        Enumeration<URL> dirs;
        try {
            dirs = getCurrentClassLoader().getResources(jarPath);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                loadClass(url, classes);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static Class<?> loadClass(String className) {
        Class<?> clz = null;
        try {
            clz = getCurrentClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            LOGGER.error("   The smartX Framework  load classFile ERROR !");
            throw new RuntimeException(e);
        }
        return clz;
    }

    private static void loadClass(final URL url, List<Class<?>> classes) {
        try {
            if (url.toString().startsWith("jar:file:") || url.toString().startsWith("wsjar:file:")) {

                // 获取jar
                JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();

                // 从此jar包 得到一个枚举类
                Enumeration<JarEntry> eje = jarFile.entries();

                // 同样的进行循环迭代
                while (eje.hasMoreElements()) {
                    // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                    JarEntry entry = eje.nextElement();
                    String name = entry.getName();
                    // 如果是以/开头的
                    if (name.charAt(0) == '/') {
                        // 获取后面的字符串
                        name = name.substring(1);
                    }
//                    // 如果前半部分和定义的包名相同
//                    if (name.startsWith(packageDirName)) {
//                        int idx = name.lastIndexOf('/');
//                        // 如果以"/"结尾 是一个包
//                        if (idx != -1) {
//                            // 获取包名 把"/"替换成"."
//                            packageName = name.substring(0, idx).replace('/', '.');
//                        }
//                        // 如果可以迭代下去 并且是一个包
//                        if ((idx != -1) || recursive) {
//                            // 如果是一个.class文件 而且不是目录
//                            if (name.endsWith(".class") && !entry.isDirectory()) {
//                                // 去掉后面的".class" 获取真正的类名
//                                String className = name.substring(packageName.length() + 1, name.length() - 6);
//                                // 添加到classes
//                                Class<?> clazz = Class.forName(packageName + '.' + className);
//                                if (null != parent && null != annotation) {
//                                    if (null != clazz.getSuperclass() &&
//                                            clazz.getSuperclass().equals(parent) && null != clazz.getAnnotation(annotation)) {
//                                        classes.add(clazz);
//                                    }
//                                    continue;
//                                }
//                                if (null != parent) {
//                                    if (null != clazz.getSuperclass() && clazz.getSuperclass().equals(parent)) {
//                                        classes.add(clazz);
//                                    }
//                                    continue;
//                                }
//                                if (null != annotation) {
//                                    if (null != clazz.getAnnotation(annotation)) {
//                                        classes.add(clazz);
//                                    }
//                                    continue;
//                                }
//                                classes.add(clazz);
//                            }
//                        }
//                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("The scan error when the user to define the view from a jar package file.", e);
        }
    }
}
