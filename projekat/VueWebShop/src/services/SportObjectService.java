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
		repo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
				
		Address adr = new Address("Beogradska 15","Beograd","21000");
		Location loc = new Location(100.6,78.7,adr);
		WorkTime work = new WorkTime(LocalTime.of(10,0,0),LocalTime.of(20,0,0));
		SportObject newCustomer = new SportObject("4","Kombank arena","Sportska hala","Bina",loc, 3.0,"C:" + File.separator + "Users" + File.separator + "KORISNIK" + File.separator + "Desktop" + File.separator+ "WebProgramiranje-PredmetniProjekat" + File.separator + "projekat" + File.separator + "VueWebShop" + File.separator + "WebContent" + File.separator + "images" + File.separator + "kombankArena.png", work);
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
		repo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<SportObject> sportObjects = repo.getAll();
		ArrayList<SportObjectDTO> retVal = new ArrayList<SportObjectDTO>();
		for (SportObject s : sportObjects) {
			retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), s.getContent(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString() ));
		}
		
		return retVal;
	}
}