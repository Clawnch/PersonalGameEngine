package GameTesting.AdvancedGui.Console;

public class Debug {

    public static void printObjectToConsole(Object classSource) {
        System.out.printf("Source: %s; %s%n", classSource.getClass(), classSource);
    }

    public static void printMessageToConsole(Object classSource, String message) {
        System.out.printf("Source: %s; Message: %s%n", classSource.getClass(), message);
    }
}
