package services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import dto.CreateSportObjectDTO;
import dto.SearchDTO;
import dto.SportObjectDTO;
import model.Location;
import model.Address;
import model.IdGenerator;
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
		if (ctx.getAttribute("id") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("id", "");
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
	
	
	@POST
	@Path("create")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SportObject createSportObject(CreateSportObjectDTO sportObjectDTO)
	{
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		int startTimeHours = Integer.parseInt(sportObjectDTO.getStartTime().split(":")[0]);
		int startTimeMinutes = Integer.parseInt(sportObjectDTO.getStartTime().split(":")[1]);
		
		int endTimeHours = Integer.parseInt(sportObjectDTO.getEndTime().split(":")[0]);
		int endTimeMinutes = Integer.parseInt(sportObjectDTO.getEndTime().split(":")[1]);
		
		SportObject sportObject = new SportObject(IdGenerator.getInstance().generateId(repo.getKeySet(), 5), sportObjectDTO.getName(), sportObjectDTO.getType(), new Location(sportObjectDTO.getLongitude(), sportObjectDTO.getLatitude(), new Address(sportObjectDTO.getStreetAndNumber(), sportObjectDTO.getCity(), sportObjectDTO.getPostalCode())), new WorkTime(LocalTime.of(startTimeHours, startTimeMinutes), LocalTime.of(endTimeHours, endTimeMinutes)), sportObjectDTO.getImage());
		
		
				
//		Address adr = new Address("Spens","Novi Sad","22000");
//		Location loc = new Location(101.1,55.9,adr);
//		WorkTime work = new WorkTime(LocalTime.of(15,0,0),LocalTime.of(23,0,0));
//		SportObject newCustomer = new SportObject("6","Spens","Hala","Grupni treninzi",loc, 4.9,"images" + File.separator + "spens.png", work);
//		Map<String, SportObject> mapa = new HashMap<String, SportObject>();
//		mapa.put(newCustomer.getId(), newCustomer);
//		repo.writeFile(mapa);
		repo.create(sportObject);
		return sportObject;
	}





	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public void uploadImage(
	    @FormDataParam("file") InputStream uploadedInputStream,
	    @FormDataParam("file") FormDataContentDisposition fileDetails) {

	   System.out.println(fileDetails.getFileName());

	   String uploadedFileLocation = "C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\WebContent\\images\\" + fileDetails.getFileName();

	   // save it
	   writeToFile(uploadedInputStream, uploadedFileLocation);

	  
	}
	
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
		try {
		      OutputStream out = new FileOutputStream(new File(
		            uploadedFileLocation));
		      int read = 0;
		      byte[] bytes = new byte[1024];

		      out = new FileOutputStream(new File(uploadedFileLocation));
		      while ((read = uploadedInputStream.read(bytes)) != -1) {
		         out.write(bytes, 0, read);
		      }
		      out.flush();
		      out.close();
		   } catch (IOException e) {
		      e.printStackTrace();
		   }
	}

	
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
	
	
	@GET
	@Path("getActiveSportObject")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SportObject getActiveSportObject() {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		String id = (String)ctx.getAttribute("id");
		return repo.read(id);
	}
	
	@POST
	@Path("transformToDTO")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SportObjectDTO transformToDTO(SportObject sportObject) {
		SportObjectDTO sportObjectDTO;
		sportObject.setStatus();
		if(sportObject.getStatus() == SportObjectStatus.Open) {
			sportObjectDTO = new SportObjectDTO(sportObject.getId(), sportObject.getName(), sportObject.getType(), sportObject.getContent(), sportObject.getLocation().toString(), sportObject.getAverageGrade(), sportObject.getImage(), sportObject.getWorkTime().toString(),"OPEN");	
		}
		else {
			sportObjectDTO = new SportObjectDTO(sportObject.getId(), sportObject.getName(), sportObject.getType(), sportObject.getContent(), sportObject.getLocation().toString(), sportObject.getAverageGrade(), sportObject.getImage(), sportObject.getWorkTime().toString(),"CLOSE");	
		}
		
		return sportObjectDTO;
	}
	
}