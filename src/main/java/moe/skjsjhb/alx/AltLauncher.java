package moe.skjsjhb.alx;

import java.lang.reflect.InvocationTargetException;

public class AltLauncher {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("This game is enhanced by Alicorn ALX.");

        ALXServer.nonce = System.getProperty("alicorn.alx.nonce");
        ALXServer server = new ALXServer();
        server.start();

        String mainClassName = System.getProperty("alicorn.alx.mainClass");
        Class<?> mainClass = Class.forName(mainClassName);
        mainClass.getMethod("main", String[].class).invoke(null, (Object) args);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                server.stop();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
