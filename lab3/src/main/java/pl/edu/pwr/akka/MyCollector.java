package pl.edu.pwr.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;

public class MyCollector extends UntypedActor {

  private ActorRef explorer;
  final private long start = System.nanoTime();
  int pendingWork;
  int numberOfFiles;

  @Override
  public void preStart() {
    explorer = getContext().actorOf(Props.create(ExploreFile.class)
                                   .withRouter(new RoundRobinPool(100)));
  }

  @Override
  public void onReceive(final Object message) {
    if(message instanceof String) {
      pendingWork++;
      String path = (String) message;
      explorer.tell(path, self());
    } else {
      pendingWork--;
      int count = (Integer) message;
      numberOfFiles += count;
    }

    if(pendingWork == 0) {
      System.out.println("Total: " + numberOfFiles);
      getContext().system().shutdown();
    }
  }
}
