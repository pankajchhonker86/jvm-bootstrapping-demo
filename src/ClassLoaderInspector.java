package src;

public class ClassLoaderInspector {
    public static void main(String[] args) {
        System.out.println("=== JVM ClassLoader Hierarchy Inspection ===\n");
        ClassLoader bootstrapLoader = String.class.getClassLoader();
        System.out.println("[1] Core String Class Loader: " + bootstrapLoader);
        System.out.println("    -> Why null? Because the Bootstrap Loader is native C/C++, not a Java object!\n");
        ClassLoader platformLoader = java.sql.Date.class.getClassLoader();
        System.out.println("[2] Platform Class Loader: " + platformLoader);
        ClassLoader appLoader = ClassLoaderInspector.class.getClassLoader();
        System.out.println("[3] Application Class Loader: " + appLoader);
        System.out.println("\n=== The Parent Delegation Chain ===");
        ClassLoader currentLoader = appLoader;
        while (currentLoader != null) {
            System.out.println("   Loaded by: " + currentLoader);
            currentLoader = currentLoader.getParent();
        }
        System.out.println("   Root Parent: Bootstrap Loader (null)");
    }
}