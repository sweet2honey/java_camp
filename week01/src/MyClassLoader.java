import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {
    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class HelloClass = null;
        try {
            //获得解码后的类
            HelloClass = myClassLoader.findClass("Hello");

            //创建类的对象
            Object obj = HelloClass.newInstance();

            //获取hello方法并执行
            Method hello = HelloClass.getMethod("hello");
            hello.invoke(obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /***
     *
     * @param name
     * @return 解码`Hello.xclass`文件后获得的类
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = null;
        String path = "week01/src/Hello.xlass";
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = decode(bytes);
        return defineClass(name, bytes, 0, bytes.length);
    }

    /***
     * 按照(x = 255 -x)规则反转字节数组
     * @param bytes 需要反转的数组
     * @return 翻转后的数组
     */
    public byte[] decode(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return bytes;
    }
}
