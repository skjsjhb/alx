package moe.skjsjhb.alx;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;


public class ALXServer extends WebSocketServer {
    public static String nonce = "";

    @Override
    public void onOpen(WebSocket ws, ClientHandshake clientHandshake) {
    }

    @Override
    public void onClose(WebSocket ws, int i, String s, boolean b) {
    }

    @Override
    public void onMessage(WebSocket ws, String s) {
        JSONObject msg = new JSONObject(s);
        String eid = msg.getString("eid");
        if (msg.has("nonce") && msg.getString("nonce").equals(nonce)) {
            String method = msg.getString("method");
            switch (method) {
                case "isAlive":
                    ws.send(buildResponse(eid, "OK"));
                    return;
                case "getMemoryUsage":
                    ws.send(buildResponse(eid, String.valueOf(getMemory())));
                    return;
                default:
                    ws.send(buildResponse(eid, "Unknown method"));
                    return;
            }
        }

        ws.send(buildResponse(eid, ""));
    }

    @Override
    public void onError(WebSocket ws, Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("ALX-Server-Port: " + getPort());
    }

    private static String buildResponse(String eid, String res) {
        JSONObject o = new JSONObject();
        o.put("eid", eid);
        o.put("res", res);
        return o.toString();
    }

    private static long getMemory() {
        return Runtime.getRuntime().totalMemory();
    }
}
