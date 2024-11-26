package FilesIO;

import org.omg.CORBA.Environment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IO {

    public static void main(String[] args) throws IOException {
       Path path0= Files.walk(Paths.get(System.getProperty("user.dir"))).filter(a->a.getFileName().toString().equals("test.xml")).findFirst().get();
        String desktopPath =System.getProperty("user.home") + "\\"+"Desktop\\test1.xml";
        Files.copy(path0.toAbsolutePath(),Paths.get(desktopPath));
    }

}
