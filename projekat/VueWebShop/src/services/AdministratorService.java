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

import model.Administrator;
import model.Gender;
import repository.AdministratorRepository;

@Path("administrators")
public class AdministratorService {

AdministratorRepository repo = new AdministratorRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("administrators") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("administrators", new AdministratorService());
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
//		Administrator newCustomer = new Administrator("Miki", "465466", "Milos", "Milic", Gender.Male, new Date(1955,8,25));
//		Map<String, Administrator> mapa = new HashMap<String, Administrator>();
//		mapa.put(newCustomer.getId(), newCustomer);
//		repo.writeFile(mapa);
//		
//		System.out.println("Created new customer: ");
//	}
}
