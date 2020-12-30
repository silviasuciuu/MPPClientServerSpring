package services;

import javafx.collections.ObservableList;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface AppServices {
    void login(AngajatOficiu user, Observer client) throws AppException, SQLException;
    void ticketSold(Bilet b) throws AppException, SQLException;
    void logout( Observer client) throws AppException, SQLException;
    List<DateTabel> getAllShows() throws AppException, SQLException, IOException;
    List<DateConcertTabel> findByDay(LocalDate zi) throws IOException, AppException;
}
