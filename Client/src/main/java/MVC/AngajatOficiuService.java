package MVC;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.*;
import services.AppException;
import services.AppServices;
import services.Observer;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class AngajatOficiuService extends UnicastRemoteObject implements Initializable, Observer {
    @FXML
    DatePicker datePicker;
    @FXML
    TextField IDSpectacolField;
    @FXML
    TextField NumeCumparatorField;
    @FXML
    TextField NumarLocuriField;
    @FXML
    Button ButonCauta;
    @FXML
    Button ButonVide;
    @FXML
    Button ButonLogout;
    @FXML
    TableView<DateTabel> tabelView;
    @FXML
    TableColumn<DateTabel, String> ArtistCol;
    @FXML
    TableColumn<DateTabel, String> IDSCol;
    @FXML
    TableColumn<DateTabel, String> DataSCol;
    @FXML
    TableColumn<DateTabel, String> LocSCol;
    @FXML
    TableColumn<DateTabel, Integer> DispCol;
    @FXML
    TableColumn<DateTabel, Integer> VandutCol;
    ObservableList<DateTabel> rez = FXCollections.observableArrayList();
    private AppServices server;
    private AngajatOficiu ao;
    List<AngajatOficiu> logati = new ArrayList<>();
    public void setAngajat(AngajatOficiu ao) {
        this.ao = ao;
    }

    public void setServer(AppServices server) {
        this.server = server;
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArtistCol.setCellValueFactory(new PropertyValueFactory<>("numeArtist"));
        IDSCol.setCellValueFactory(new PropertyValueFactory<>("IDS"));
        DataSCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        LocSCol.setCellValueFactory(new PropertyValueFactory<>("locatie"));
        DispCol.setCellValueFactory(new PropertyValueFactory<>("disponibil"));
        VandutCol.setCellValueFactory(new PropertyValueFactory<>("vandut"));


    }

    public void init() throws IOException, SQLException, AppException {
        rez.setAll(server.getAllShows());
        tabelView.setItems(rez);
        setColor();
    }

    public AngajatOficiuService(AppServices server) throws IOException, RemoteException {
        this.server = server;

    }

    public AngajatOficiuService() throws IOException {

    }


    public void Cauta(ActionEvent actionEvent) throws IOException, AppException, ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate d=datePicker.getValue();
        String s=d.toString();
        Date date = formatter.parse(s);


        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource("ConcerteCautate.fxml"));
        Parent root=loader.load();

        ConcerteCautateController ctrl =
                loader.<ConcerteCautateController>getController();
        ctrl.setServer(server);
        ctrl.setData(new SimpleDateFormat("dd-MM-yyyy").format(date));
        ctrl.init();
        Stage primaryStage=new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    public void Vinde(ActionEvent actionEvent) throws AppException, SQLException, IOException {
        this.server.ticketSold(new Bilet(IDSpectacolField.getText(), NumeCumparatorField.getText(), Integer.parseInt(NumarLocuriField.getText())));
        init();
        IDSpectacolField.clear();
        NumeCumparatorField.clear();
        NumarLocuriField.clear();

    }



    public void setColor() {

        tabelView.setRowFactory(tv -> new TableRow<DateTabel>() {
            @Override
            public void updateItem(DateTabel item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setStyle("");
                } else if (item.getDisponibil() == 0) {
                    setStyle("-fx-background-color: red;");
                }
            }
        });
    }

    public void butonLogutPressed(ActionEvent evt) throws IOException {
        logout();
        ((Node)(evt.getSource())).getScene().getWindow().hide();

    }

    void logout() {
        try {
            server.logout(this);
        } catch (AppException | SQLException e) {
            System.out.println("Logout error " + e);
        }

    }

    @Override
    public void tickedSold(List<DateTabel> sp) {
        Platform.runLater(()->this.rez.setAll(sp));

    }

    private static void showErrorMessage(String err) {
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("Error message!");
        message.setContentText(err);
        message.showAndWait();
    }
}
