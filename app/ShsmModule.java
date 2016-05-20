import java.time.Clock;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import play.libs.Akka;
import akka.actor.Cancellable;

import com.google.inject.AbstractModule;

/**
 * This class is a Guice module that tells Guice how to bind several different
 * types. This Guice module is created when the Play application starts.
 *
 * Play will automatically use any class called `Module` that is in the root
 * package. You can create modules in other locations by adding
 * `play.modules.enabled` settings to the `application.conf` configuration file.
 */
public class ShsmModule extends AbstractModule {
	private Cancellable scheduler;

	@Override
	public void configure() {
		// Use the system clock as the default implementation of Clock
		bind(Clock.class).toInstance(Clock.systemDefaultZone());
		// Ask Guice to create an instance of ApplicationTimer when the
		// application starts.
	}
	
//	@Provides
//    public Datastore getMongoDatastore(ApplicationLifecycle lifecycle) {
//        Config dbConf = ConfigFactory.load().getConfig("datastore.shsm");
//        MongoClient mongoClient = new MongoClient(dbConf.getString(MongoConstants.IP),
//                dbConf.getInt(MongoConstants.PORT_PROPERTY));
//        Morphia morphia = new Morphia();
//        lifecycle.addStopHook(() -> {
//            mongoClient.close();
//            return CompletableFuture.completedFuture(null);
//        });
//        return morphia.createDatastore(mongoClient, dbConf.getString(MongoConstants.DBNAME_PROPERTY));
//    }


	public static int nextExecutionInSeconds(int hour, int minute) {
		return Seconds.secondsBetween(new DateTime(), nextExecution(hour, minute)).getSeconds();
	}

	public static DateTime nextExecution(int hour, int minute) {
		DateTime next = new DateTime().withHourOfDay(hour).withMinuteOfHour(minute).withSecondOfMinute(0)
				.withMillisOfSecond(0);

		return (next.isBeforeNow()) ? next.plusHours(24) : next;
	}
}
