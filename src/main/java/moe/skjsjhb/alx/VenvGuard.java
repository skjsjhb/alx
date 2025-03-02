package moe.skjsjhb.alx;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class VenvGuard {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        String gameMainClass = System.getProperty("alicorn.alx.gameMainClass");
        String venvDir = System.getProperty("alicorn.alx.venvDir");
        String srcDir = System.getProperty("alicorn.alx.srcDir");

        System.out.println("=== Alicorn ALX VENV Guard ===");
        System.out.println("This game was started in a virtual container at: " + venvDir);

        Class<?> mainClass = Class.forName(gameMainClass);
        Method mainMethod = mainClass.getMethod("main", (String[].class));
        mainMethod.invoke(null, (Object) args);

        System.out.println("Unmounting container to " + srcDir);
        FileUtils.deleteQuietly(new File(srcDir));
        FileUtils.moveDirectoryToDirectory(new File(venvDir), new File(srcDir), true);
        FileUtils.deleteQuietly(new File(venvDir));
    }
}
