package services;

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
	/*@GET
	@Path("createAuto")	
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public void createAdministratorAuto()
	{
		repo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
				
		Address adr = new Address("Branimira Cosica 8","NoviSad","21000");
		Location loc = new Location(11.6,32.7,adr);
		WorkTime work = new WorkTime(LocalTime.of(20,30,0),LocalTime.of(23,30,0));
		SportObject newCustomer = new SportObject("5","Stadion Karadjordje","Sportski teren","Fudbalske utakmice",loc, 3.60,"C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\WebContent\\karadjordje.png", work);
		//Map<String, SportObject> mapa = new HashMap<String, SportObject>();
		//mapa.put(newCustomer.getId(), newCustomer);
		//repo.writeFile(mapa);
		repo.create(newCustomer);
		System.out.println("Created new customer: ");
	}
	*/
	@GET
	@Path("getAll")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<SportObject> getAll() {
		repo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		return repo.getAll();
	}
}