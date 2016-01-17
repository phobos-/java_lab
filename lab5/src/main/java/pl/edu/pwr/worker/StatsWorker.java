package pl.edu.pwr.worker;

import pl.edu.pwr.service.StatsService;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Pawel on 2014-12-05.
 */
public class StatsWorker {

    private StatsService statsService;
    private Logger logger;

    public StatsWorker(StatsService statsService) {
        this.statsService = statsService;

        logger = Logger.getLogger("Info");
        FileHandler fh;
        //not writing into the console
        logger.setUseParentHandlers(false);

        try {
            fh = new FileHandler("lab5/log.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateStats(){
        logger.info(" Files currently processed: " + statsService.getStats());
    }
}
