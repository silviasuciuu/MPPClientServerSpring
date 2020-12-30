package services;

import model.AngajatOficiu;
import model.Bilet;
import model.DateTabel;
import model.Spectacol;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Observer extends Remote {



    void tickedSold(List<DateTabel> sp) throws RemoteException;
}
