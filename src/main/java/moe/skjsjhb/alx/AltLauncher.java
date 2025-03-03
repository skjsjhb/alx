package moe.skjsjhb.alx;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AltLauncher {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("This game is enhanced by Alicorn ALX.");

        ALXServer.nonce = System.getProperty("alicorn.alx.nonce");
        ALXServer server = new ALXServer();
        server.start();

        String mainClassName = System.getProperty("alicorn.alx.mainClass");
        Class<?> mainClass = Class.forName(mainClassName);
        Method mainMethod = mainClass.getMethod("main", String[].class);

        try {
            mainMethod.invoke(null, (Object) args);
        } catch (Exception e) {
            try {
                server.stop();
            } catch (InterruptedException ignored) {
            }

            System.exit(1);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                server.stop();
            } catch (InterruptedException ignored) {
            }
        }));
    }
}
