import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.sql.*;
import com.sun.net.httpserver.*;

public class MethodologyServer {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shopsphere", "root", "123456");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT image_path1, image_path2 FROM methodology_images");

            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            server.createContext("/images", exchange -> {
                try {
                    StringBuilder response = new StringBuilder("<html><head><title>Methodology Images</title></head><body>");
                    response.append("<h1>Software Development Methodologies</h1>");
                    response.append("<p>This presentation shows images representing foundational principles and popular models.</p>");
                    while (rs.next()) {
                        response.append("<h2>Comparison of SDLC Methodologies</h2>");
                        response.append("<img src='/static/" + rs.getString("image_path1")
                                + "' alt='Comparison of SDLC Methodologies' width='400'>");
                        response.append("<h2>Popular SDLC Models</h2>");
                        response.append("<img src='/static/" + rs.getString("image_path2") + "' alt='Popular SDLC Models' width='400'>");
                    }
                    response.append("</body></html>");
                    byte[] bytes = response.toString().getBytes();
                    exchange.sendResponseHeaders(200, bytes.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(bytes);
                    os.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            server.createContext("/static", exchange -> {
                URI uri = exchange.getRequestURI();
                String path = uri.getPath().replace("/static/", "");
                File file = new File("C:/project/Civilization/Presntation four/static", path);

                if (file.exists() && !file.isDirectory()) {
                    byte[] bytes = Files.readAllBytes(file.toPath());
                    exchange.sendResponseHeaders(200, bytes.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(bytes);
                    os.close();
                } else {
                    String error = "File not found: " + path;
                    exchange.sendResponseHeaders(404, error.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(error.getBytes());
                    os.close();
                }
            });

            server.start();
            System.out.println("Server started at http://localhost:8080/images");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}


