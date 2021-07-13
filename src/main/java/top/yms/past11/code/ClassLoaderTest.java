package top.yms.past11.code;

import java.io.IOException;

import java.io.InputStream;

public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,

            InstantiationException {

        ClassLoader myLoader = new ClassLoader() {

            @Override

            public Class<?> loadClass(String name) throws ClassNotFoundException {

                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";

                InputStream inputStream = getClass().getResourceAsStream(fileName);

                if (inputStream == null) {

                    return super.loadClass(name);

                }

                try {

                    byte[] b = new byte[inputStream.available()];

                    inputStream.read(b);

                    return defineClass(name, b, 0, b.length);

                } catch (IOException e) {

                    throw new ClassNotFoundException();

                }

            }

        };

        Object obj = myLoader.loadClass("top.yms.past11.code.ClassLoaderTest").newInstance();

        System.out.println(obj.getClass());

        System.out.println(obj instanceof ClassLoaderTest);

        ClassLoaderTest classLoaderTest = new ClassLoaderTest();

        System.out.println(classLoaderTest.getClass());

        System.out.println(classLoaderTest instanceof ClassLoaderTest);

    }

}
