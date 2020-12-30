import MVC.AngajatOficiuService;
import MVC.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.AngajatOficiu;
import objectprotocol.ClientWorker;
import objectprotocol.ProxyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.AppServices;

import java.io.IOException;
import java.util.Properties;

public class StartClient extends Application {
    private static int defaultPort = 55556;
    private static String defaultServer = "localhost";


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        ApplicationContext factory=new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        AppServices server=(AppServices)factory.getBean("appService");
        System.out.println("Obtainned a reference to remote server");



        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource("Login.fxml"));
        Parent root=loader.load();

        Login ctrl =
                loader.<Login>getController();
        ctrl.setServer(server);




        FXMLLoader cloader = new FXMLLoader(
                getClass().getClassLoader().getResource("AngajatView.fxml"));

        Parent croot=cloader.load();



        AngajatOficiuService chatCtrl =
                cloader.<AngajatOficiuService>getController();
        chatCtrl.setServer(server);

        ctrl.setAngajatContrller(chatCtrl);
        ctrl.setParent(croot);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

















    }
}
