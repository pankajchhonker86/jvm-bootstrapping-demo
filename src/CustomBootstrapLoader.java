package src;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomBootstrapLoader extends ClassLoader {
    private String classDir;

    public CustomBootstrapLoader(String classDir) {
        this.classDir = classDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String targetName = name.replace("src.", "");
            Path classPath = Paths.get(classDir, targetName.replace('.', '/') + ".class");
            byte[] classBytes = Files.readAllBytes(classPath);
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Could not find or read class: " + name, e);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== Custom Bootstrapping Demonstration ===");
        String externalPath = "./external_modules";
        CustomBootstrapLoader myLoader = new CustomBootstrapLoader(externalPath);

        try {
            System.out.println("\nTrying to load 'SecretMessage' normally via Application Loader...");
            Class.forName("src.SecretMessage");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Failed! The standard application classpath knows nothing about 'SecretMessage'.");
        }

        System.out.println("\nBootstrapping 'SecretMessage' using our Custom ClassLoader...");
        Class<?> secretClass = myLoader.loadClass("src.SecretMessage");
        System.out.println("✅ Class loaded successfully via: " + secretClass.getClassLoader());

        Object secretInstance = secretClass.getDeclaredConstructor().newInstance();
        Method method = secretClass.getMethod("reveal");
        method.invoke(secretInstance);
    }
}