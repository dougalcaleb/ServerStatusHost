package io.github.dougalcaleb.serverstatushost;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class Webhook {

    static String WebhookURL;

    public static void playerJoin(Player player, Server server, String url) {
        if (!ServerStatusHost.checkURL(url)) {
            return;
        }

        try {
            byte[] out = ("{\"content\": \"PLAYER_JOIN|" + player.getName() + "\"}").getBytes(StandardCharsets.UTF_8);

            URL botUrl = new URL(url);
            URLConnection con = botUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setFixedLengthStreamingMode(out.length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
        }
        catch(IOException e) {
            ServerStatusHost.logError(e);
        }
    }

    public static void playerLeave(Player player, Server server, String url) {
        if (!ServerStatusHost.checkURL(url)) {
            return;
        }

        try {
            byte[] out = ("{\"content\":" + "\"PLAYER_LEAVE|" + player.getName() + "\"}").getBytes(StandardCharsets.UTF_8);

            URL botUrl = new URL(url);
            URLConnection con = botUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setFixedLengthStreamingMode(out.length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
        }
        catch(IOException e) {
            ServerStatusHost.logError(e);
        }
    }

    public static void startup(String url) {
        if (!(ServerStatusHost.checkURL(url))) {
            return;
        }

        WebhookURL = url;

        try {
            byte[] out = ("{\"content\": \"SERVER_INIT\"}").getBytes(StandardCharsets.UTF_8);

            URL botUrl = new URL(url);
            URLConnection con = botUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setFixedLengthStreamingMode(out.length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
        }
        catch(IOException e) {
            ServerStatusHost.logError(e);
        }
    }

    public static void shutdown(String url) {
        if (!ServerStatusHost.checkURL(url)) {
            return;
        }

        try {
            byte[] out = ("{\"content\": \"SERVER_SHUTDOWN\"}").getBytes(StandardCharsets.UTF_8);

            URL botUrl = new URL(url);
            URLConnection con = botUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setFixedLengthStreamingMode(out.length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
        }
        catch(IOException e) {
            ServerStatusHost.logError(e);
        }
    }

    public static void healthUpdate() {
        if (!ServerStatusHost.checkURL(WebhookURL)) {
            return;
        }

        try {
            byte[] out = ("{\"content\": \"SERVER_REPORT|" + String.join("|", DataHandler.getServerHealth().split("\\r?\\n")) + "\"}").getBytes(StandardCharsets.UTF_8);

            URL botUrl = new URL(WebhookURL);
            URLConnection con = botUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setFixedLengthStreamingMode(out.length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
        }
        catch(IOException e) {
            ServerStatusHost.logError(e);
        }
    }
}
