
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class MainMenu {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        // String a="collections."+scanner.nextLine();
        // List<String> pck= Package.getPackages()
        Reflections reflection = new Reflections("", ClassLoader.getSystemClassLoader(), new SubTypesScanner(false));
        Set<Class<?>> classes = reflection.getSubTypesOf(Object.class);
        List<String> mainList = classes.stream().map(a->a.getName()).sorted().collect(toList());
        String input ="";
        while(true) {
                System.out.println();
                System.out.println("select class number");
                System.out.println("0 " + "exit");
                mainList.forEach(a -> {if(!a.contains("MainMenu")){System.out.println(mainList.indexOf(a) + 1 + " " + a.split("\\.")[a.split("\\.").length - 1]);}});
                input = scanner.nextLine();
            if(input.equals("0"))
            {
                System.exit(0);
            }
            String finalInput = input;
            classes.stream().map(a -> a.getName()).sorted().filter(a -> a.contains(mainList.get(Integer.parseInt(finalInput)-1))).findFirst()
                    .ifPresent(a -> {
                        Class c = null; // if you want to specify a class
                        try {
                            c = Class.forName(a);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        Method meth = null;
                        try {
                            meth = c.getMethod("main", String[].class);
                        } catch (NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                        String[] params = null; // init params accordingly
                        try {
                            meth.invoke(null, (Object) params);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }

                    });

        }
    }
}
