package MVC;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Artist;
import model.DateConcertTabel;
import model.DateTabel;
import services.AppException;
import services.AppServices;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConcerteCautateController extends UnicastRemoteObject implements Initializable {
    @FXML
    TableView<DateConcertTabel> tabelView;
    @FXML
    TableColumn<DateConcertTabel, String> ArtistCol;
    @FXML
    TableColumn<DateConcertTabel, String> OraInceperiiCol;
    @FXML
    TableColumn<DateConcertTabel, String> LocSCol;
    @FXML
    TableColumn<DateConcertTabel, Integer> DispCol;
    @FXML
    Label DateLabel;
    ObservableList<DateConcertTabel> rez = FXCollections.observableArrayList();

    private AppServices server;

    protected ConcerteCautateController() throws RemoteException {
    }

    public void setServer(AppServices server) {
        this.server = server;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArtistCol.setCellValueFactory(new PropertyValueFactory<>("nume"));
        OraInceperiiCol.setCellValueFactory(new PropertyValueFactory<>("ora"));
        LocSCol.setCellValueFactory(new PropertyValueFactory<>("locatie"));
        DispCol.setCellValueFactory(new PropertyValueFactory<>("nrDisp"));



    }

    void setData(String data) {
        DateLabel.setText(data);

    }



    public void init() throws IOException, AppException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        rez.setAll(server.findByDay(LocalDate.parse(DateLabel.getText(), formatter)));
        tabelView.setItems(rez);
        setColor();
    }


    public void setColor() {

        tabelView.setRowFactory(tv -> new TableRow<DateConcertTabel>() {
            @Override
            public void updateItem(DateConcertTabel item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setStyle("");
                } else if (item.getNrDisp() == 0) {
                    setStyle("-fx-background-color: red;");
                }
            }
        });
    }

}
