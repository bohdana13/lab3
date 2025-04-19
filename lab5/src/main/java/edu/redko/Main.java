package edu.redko;

/*
  @author User
  @project Default (Template) Project
  @class ${NAME}
  @version 1.0.0
  @since 19.04.2025 - 19.47
*///TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/v1/items", new ItemHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server running on http://localhost:8080/");
    }
}

