package Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * Created by MA_Laptop on 6/21/2017.
 */
public class TrialServiceClass extends Service<ObservableList<Long>>
{

    String ipAddress;
    int port;

    @Override
    protected Task<ObservableList<Long>> createTask() {
        /*ObservableList<Long> results = FXCollections.<Long>observableArrayList();
        System.out.println("in trialservice class createtask");*/
        return null;
    }
}
