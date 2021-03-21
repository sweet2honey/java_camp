import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;

public class PrintClassLoaderPath {
    public static void main(String[] args) {
//        new PrintClassLoaderPath().PrintBootstrapClassPath();
    }

    public void PrintBootstrapClassPath(){
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("Launcher.getBootstrapClassPath().getURLs():");
        for (URL url: urLs) {
            System.out.println(url.toExternalForm());
        }
    }
}
