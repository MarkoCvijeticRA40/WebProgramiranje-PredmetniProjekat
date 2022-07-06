package services;

import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import repository.ScheduledTrainingsRepository;

@Path("scheduledTrainings")
public class ScheduledTrainingsService {

	ScheduledTrainingsRepository repo = new ScheduledTrainingsRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("scheduledTrainings") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("scheduledTrainings", new ScheduledTrainingsService());
		}
	}
	
}
