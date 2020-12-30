package MVC;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.AngajatOficiu;
import services.AppException;
import services.AppServices;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login extends UnicastRemoteObject {
    private AppServices server;
    @FXML
    private TextField UserField;
    @FXML
    private Button ButonLogare;
    Parent mainParent;
    private AngajatOficiuService angajatCtrl;
    private Stage primayStage;

    public Login() throws IOException, RemoteException {
        System.out.println("sever in loginnnnnnnnnnnnnnnn"+getServer());
    }

    public void setParent(Parent p){
        mainParent=p;
    }
    @FXML
    private PasswordField ParolaField;
    public Stage stage1;
    private AngajatOficiuService ctrl = new AngajatOficiuService();

    public void setServer(AppServices server) {

        this.server = server;

    }




    public AppServices getServer() {
        return server;
    }



    public void ButonLogInPressed(ActionEvent actionEvent) throws SQLException, IOException, AppException {

        AngajatOficiu a=new AngajatOficiu(UserField.getText(), ParolaField.getText());

        try{
            server.login(a, this.angajatCtrl);
            login(a.getUsername());
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }catch (Exception e){
            e.printStackTrace();
            showErrorMessage(e.getMessage());
        }


    }
    public void login(String username) throws IOException, AppException, SQLException {
        AngajatOficiu a=new AngajatOficiu(UserField.getText(), ParolaField.getText());
        Stage primaryStage=new Stage();
        primaryStage.setScene(new Scene(mainParent));
        primaryStage.show();
        angajatCtrl.init();
        angajatCtrl.setAngajat(a);



    }

    public void setAngajatContrller(AngajatOficiuService angajatCtrl) {
            this.angajatCtrl = angajatCtrl;
        }


    public void setStage(Stage primaryStage) {
        this.primayStage=primaryStage;
    }
    private static void showErrorMessage(String err){
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("Error message!");
        message.setContentText(err);
        message.showAndWait();
    }
}

