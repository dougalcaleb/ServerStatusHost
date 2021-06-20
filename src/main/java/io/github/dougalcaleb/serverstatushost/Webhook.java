package io.github.dougalcaleb.serverstatushost;

import okhttp3.*;
import org.bukkit.Server;
import org.bukkit.entity.Player;
//import org.json.JSONObject;
import org.json.simple.JSONObject;

import java.io.IOException;

public class Webhook {

    public static void playerJoin(Player player, Server server, String url) {
        if (!ServerStatusHost.checkURL(url)) {
            return;
        }

        try {
            JSONObject obj = new JSONObject();
            obj.put("content", player.getName() + " joined. online: " + server.getOnlinePlayers().size());

            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
            Request req = new Request.Builder().url(url).post(body).build();

            Response res = client.newCall(req).execute();
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
            JSONObject obj = new JSONObject();
            obj.put("content", player.getName() + " left. online: " + (server.getOnlinePlayers().size()-1));

            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
            Request req = new Request.Builder().url(url).post(body).build();

            Response res = client.newCall(req).execute();
        }
        catch(IOException e) {
            ServerStatusHost.logError(e);
        }
    }

    public static void startup(String url) {
        ServerStatusHost.plugin.getLogger().info("Webhook.Startup called");
        if (!(ServerStatusHost.checkURL(url))) {
            return;
        }

        ServerStatusHost.plugin.getLogger().info("Passed URL check");

        try {
            JSONObject obj = new JSONObject();
            obj.put("content", "online");

            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
            Request req = new Request.Builder().url(url).post(body).build();

            Response res = client.newCall(req).execute();
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
            JSONObject obj = new JSONObject();
            obj.put("content", "offline");

            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
            Request req = new Request.Builder().url(url).post(body).build();

            Response res = client.newCall(req).execute();
        }
        catch(IOException e) {
            ServerStatusHost.logError(e);
        }
    }
}
