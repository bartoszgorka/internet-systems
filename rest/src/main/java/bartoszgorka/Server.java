package bartoszgorka;

import bartoszgorka.models.DB;
import bartoszgorka.rest.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class Server {
    public static void main(String[] args) {
        DB db = DB.getInstance();
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8000).build();
        ResourceConfig config = new ResourceConfig(Students.class, Student.class, Courses.class, Course.class, Grades.class, Grade.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}