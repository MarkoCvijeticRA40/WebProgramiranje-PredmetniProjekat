package services;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Gender;
import model.Manager;
import repository.ManagerRepository;

@Path("managers")
public class ManagerService {

	ManagerRepository repo = new ManagerRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("managers") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("managers", new AdministratorService());
		}
	}
	
//	@GET
//	@Path("createAuto")	
//	@Produces(MediaType.TEXT_PLAIN)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void createAdministratorAuto()
//	{
//		//repo.setBasePath(getDataDirPath());
//				
//		Manager newCustomer = new Manager("Puksa", "a4ssw5", "Predrag", "Markovic", Gender.Male, new Date(1989,10,18));
//		Map<String, Manager> mapa = new HashMap<String, Manager>();
//		mapa.put(newCustomer.getId(), newCustomer);
//		repo.writeFile(mapa);
//		
//		System.out.println("Created new customer: ");
//	}
}
