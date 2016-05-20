package controllers;

import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import com.egnaro.shsm.entity.MonitoredNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import play.Logger;
import play.libs.Akka;
import play.mvc.*;
import scala.concurrent.duration.Duration;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class ApplicationController extends Controller {

	/**
	 * An action that renders an HTML page with a welcome message. The
	 * configuration in the <code>routes</code> file means that this method will
	 * be called when the application receives a <code>GET</code> request with a
	 * path of <code>/</code>.
	 */
	public Result index() {
		schedule();
		Logger.error("***************************8");
		return ok(index.render("Your new application is ready."));
	}

	public Result monitoredNode() {
		String incomeMonitoredNodeJson = request().body().asText();
		Gson gson = new GsonBuilder().create();
		if (incomeMonitoredNodeJson == null) {
			return status(BAD_REQUEST);
		}
		MonitoredNode monitoredNode = gson.fromJson(incomeMonitoredNodeJson, MonitoredNode.class);
		return ok();
	}

	private void schedule() {

//		Akka.system()
//				.scheduler()
//				.schedule(Duration.create(0, TimeUnit.MILLISECONDS), Duration.create(1, TimeUnit.SECONDS),
//						new Runnable() {
//							@Override
//							public void run() {
//								System.out.println("now");
//								;
//							}
//						}, Akka.system().dispatcher());

	}

	public static int nextExecutionInSeconds(int hour, int minute) {
		return Seconds.secondsBetween(new DateTime(), nextExecution(hour, minute)).getSeconds();
	}

	public static DateTime nextExecution(int hour, int minute) {
		DateTime next = new DateTime().withHourOfDay(hour).withMinuteOfHour(minute).withSecondOfMinute(0)
				.withMillisOfSecond(0);

		return (next.isBeforeNow()) ? next.plusHours(24) : next;
	}

}
