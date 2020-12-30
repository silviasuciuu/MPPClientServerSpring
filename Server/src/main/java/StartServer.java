
import network.utils.servers.AbstractServer;
import network.utils.servers.ConcurentServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repos.AngajatOficiuRepo;
import repos.BiletRepo;
import server.ServicesImpl;
import services.AppServices;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class StartServer {

    private static int defaultPort=55556;
    public static void main(String[] args) throws IOException {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");

    }
}
