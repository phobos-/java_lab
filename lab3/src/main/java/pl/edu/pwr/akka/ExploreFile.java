package pl.edu.pwr.akka;

import akka.actor.UntypedActor;

import java.io.File;

public class ExploreFile extends UntypedActor {
  @Override
  public void onReceive(final Object message) throws Exception {
    String path = (String) message;

    int count = 0;
    File file = new File(path);
    File[] files = file.listFiles();
    if(files != null) {
      for(File child : files) {
        if(child.isFile())
          count++;
        else
          sender().tell(child.getPath(), self());
      }
    }

    sender().tell(count, self());
  }
}
