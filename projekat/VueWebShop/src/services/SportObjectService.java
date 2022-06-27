package services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.SearchDTO;
import dto.SportObjectDTO;
import dto.SportObjectViewDTO;
import model.SportObject;
import model.SportObjectStatus;
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
	
//	@GET
//	@Path("createAuto")	
//	@Produces(MediaType.TEXT_PLAIN)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void createAdministratorAuto()
//	{
//		repo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
//				
//		Address adr = new Address("Spens","Novi Sad","22000");
//		Location loc = new Location(101.1,55.9,adr);
//		WorkTime work = new WorkTime(LocalTime.of(15,0,0),LocalTime.of(23,0,0));
//		SportObject newCustomer = new SportObject("6","Spens","Hala","Grupni treninzi",loc, 4.9,"images" + File.separator + "spens.png", work);
////		Map<String, SportObject> mapa = new HashMap<String, SportObject>();
////		mapa.put(newCustomer.getId(), newCustomer);
////		repo.writeFile(mapa);
//		repo.create(newCustomer);
//		System.out.println("Created new customer: ");
//	}
	
	@GET
	@Path("getAll")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<SportObjectDTO> getAll() {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<SportObject> sportObjects = repo.getAll();
		ArrayList<SportObjectDTO> retVal = new ArrayList<SportObjectDTO>();
		
		for (SportObject s : sportObjects) {			
			s.setStatus();
			if(s.getStatus() == SportObjectStatus.Open) {
				retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), s.getContent(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"OPEN"));	
			}
		}
		
		for (SportObject s1 : sportObjects) {			
			s1.setStatus();
			if(s1.getStatus() == SportObjectStatus.Close) {
				retVal.add(new SportObjectDTO(s1.getId(), s1.getName(), s1.getType(), s1.getContent(), s1.getLocation().toString(), s1.getAverageGrade(), s1.getImage(), s1.getWorkTime().toString(),"CLOSE"));	
												   }
		}
		
			return retVal;
	}
	
	
	@POST
	@Path("search")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<SportObjectDTO> search(SearchDTO search) {
		if (search.getSearchText().isBlank()) {
			return getAll();
		}
		
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<SportObjectDTO> retVal = new ArrayList<SportObjectDTO>();
		ArrayList<SportObject> sportObjects = repo.getAll();
		
		for (SportObject s : sportObjects) {
			if (s.getName().toLowerCase().trim().contains(search.getSearchText().toLowerCase().trim())) {	
				s.setStatus();
				if(s.getStatus() == SportObjectStatus.Open) {
					retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), s.getContent(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"OPEN"));	
				}
				else {
					retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), s.getContent(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"CLOSE"));	
				}
			}
		}
		
		for (SportObject s : sportObjects) {
			if (s.getType().toLowerCase().trim().contains(search.getSearchText().toLowerCase().trim())) {	
				s.setStatus();
				if(s.getStatus() == SportObjectStatus.Open) {
					int cnt = 0;
					for (SportObjectDTO so : retVal) {
						if (so.getId().equals(s.getId()))
							cnt++;
					}
					if (cnt == 0)
						retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), s.getContent(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"OPEN"));	
				}
				else {
					int cnt = 0;
					for (SportObjectDTO so : retVal) {
						if (so.getId().equals(s.getId()))
							cnt++;
					}
					if (cnt == 0)
						retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), s.getContent(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"CLOSE"));	
				}
			}
		}
		
		for (SportObject s : sportObjects) {
			if (s.getLocation().getAddress().getCity().toLowerCase().trim().contains(search.getSearchText().toLowerCase().trim())) {	
				s.setStatus();
				if(s.getStatus() == SportObjectStatus.Open) {
					int cnt = 0;
					for (SportObjectDTO so : retVal) {
						if (so.getId().equals(s.getId()))
							cnt++;
					}
					if (cnt == 0)
						retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), s.getContent(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"OPEN"));	
				}
				else {
					int cnt = 0;
					for (SportObjectDTO so : retVal) {
						if (so.getId().equals(s.getId()))
							cnt++;
					}
					if (cnt == 0)
						retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), s.getContent(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"CLOSE"));	
				}
			}
		}
		
		for (SportObject s : sportObjects) {
			if (Double.toString(s.getAverageGrade()).toLowerCase().trim().contains(search.getSearchText().toLowerCase().trim())) {	
				s.setStatus();
				if(s.getStatus() == SportObjectStatus.Open) {
					int cnt = 0;
					for (SportObjectDTO so : retVal) {
						if (so.getId().equals(s.getId()))
							cnt++;
					}
					if (cnt == 0)
						retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), s.getContent(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"OPEN"));	
				}
				else {
					int cnt = 0;
					for (SportObjectDTO so : retVal) {
						if (so.getId().equals(s.getId()))
							cnt++;
					}
					if (cnt == 0)
						retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), s.getContent(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"CLOSE"));	
				}
			}
		}
		
		return retVal;
	}
	
	@POST
	@Path("setActiveSportObject")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void setActiveSportObject(SportObject sportObject) {
		ctx.setAttribute("sportobject", sportObject.getId());
	}
	
	@GET
	@Path("getActiveSportObject")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SportObject getActiveSportObject() {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		String id = (String)ctx.getAttribute("id");
		return repo.read(id);
	}
}