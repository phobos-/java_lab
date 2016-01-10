package pl.edu.pwr.worker;

import pl.edu.pwr.service.StatsService;

/**
 * Created by Pawel on 2014-12-05.
 */
public class StatsWorker {

    private StatsService statsService;

    public StatsWorker(StatsService statsService) {
        this.statsService = statsService;
    }

    public void generateStats(){
        //in thread
    }
}
