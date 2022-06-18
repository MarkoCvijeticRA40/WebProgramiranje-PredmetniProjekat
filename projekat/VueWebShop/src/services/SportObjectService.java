package services;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.SportObjectDTO;
import model.Address;
import model.Location;
import model.SportObject;
import model.SportObjectStatus;
import model.WorkTime;
import repository.SportObjectRepository;

@Path("sportobject")
public class SportObjectService {

SportObjectRepository repo = new SportObjectRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("sportobject") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("sportobject", new SportObjectService());
		}
	}
	
	@GET
	@Path("createAuto")	
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public void createAdministratorAuto()
	{
		repo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
				
		Address adr = new Address("Spens","Novi Sad","22000");
		Location loc = new Location(101.1,55.9,adr);
		WorkTime work = new WorkTime(LocalTime.of(15,0,0),LocalTime.of(23,0,0));
		SportObject newCustomer = new SportObject("6","Spens","Hala","Grupni treninzi",loc, 4.9,"images" + File.separator + "spens.png", work);
//		Map<String, SportObject> mapa = new HashMap<String, SportObject>();
//		mapa.put(newCustomer.getId(), newCustomer);
//		repo.writeFile(mapa);
		repo.create(newCustomer);
		System.out.println("Created new customer: ");
	}
	
	@GET
	@Path("getAll")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<SportObjectDTO> getAll() {
		repo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<SportObject> sportObjects = repo.getAll();
		ArrayList<SportObjectDTO> retVal = new ArrayList<SportObjectDTO>();
		for (SportObject s : sportObjects) 
		{			
			s.setStatus();
			if(s.getStatus() == SportObjectStatus.Open) {
				retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), s.getContent(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"OPEN"));	
			}
		}
			for (SportObject s1 : sportObjects) 
			{			
				s1.setStatus();
				if(s1.getStatus() == SportObjectStatus.Close) {
					retVal.add(new SportObjectDTO(s1.getId(), s1.getName(), s1.getType(), s1.getContent(), s1.getLocation().toString(), s1.getAverageGrade(), s1.getImage(), s1.getWorkTime().toString(),"CLOSE"));	
															   }
		}
			return retVal;
	}  
}
	