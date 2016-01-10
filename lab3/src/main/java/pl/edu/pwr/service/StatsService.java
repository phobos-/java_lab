package pl.edu.pwr.service;

/**
 * Created by Pawel on 2014-12-05.
 */
public class StatsService {

    private FileSender sender;

    public StatsService(FileSender sender) {
        this.sender =sender;
    }

    public int getStats(){
        return sender.getStats()/10;
    }
}
