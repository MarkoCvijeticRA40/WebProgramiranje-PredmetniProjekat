package services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalTime;
import java.util.ArrayList;

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

import dto.AddContentDTO;
import dto.CreateSportObjectDTO;
import dto.CustomerDTO;
import dto.IdDTO;
import dto.SearchDTO;
import dto.SportObjectDTO;
import model.Address;
import model.Content;
import model.Customer;
import model.IdGenerator;
import model.Location;
import model.SportObject;
import model.SportObjectStatus;
import model.WorkTime;
import repository.CustomerRepository;
import repository.SportObjectRepository;

@Path("sportobject")
public class SportObjectService {

SportObjectRepository repo = new SportObjectRepository();
CustomerRepository customerRepo = new CustomerRepository();
	
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
			StringBuilder sb = new StringBuilder("");
			if (s.getContent() != null) {
				for (Content c : s.getContent()) {
					sb.append("\n" + c.getName());
				}
			}
			if(s.getStatus() == SportObjectStatus.Open) {
				retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), sb.toString(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"OPEN"));	
			}
		}
		
		for (SportObject s1 : sportObjects) {			
			s1.setStatus();
			StringBuilder sb = new StringBuilder("");
			if (s1.getContent() != null) {
				for (Content c : s1.getContent()) {
					sb.append("\n" + c.getName());
				}
			}
			if(s1.getStatus() == SportObjectStatus.Close) {
				retVal.add(new SportObjectDTO(s1.getId(), s1.getName(), s1.getType(), sb.toString(), s1.getLocation().toString(), s1.getAverageGrade(), s1.getImage(), s1.getWorkTime().toString(),"CLOSE"));	
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
				StringBuilder sb = new StringBuilder("");
				if (s.getContent() != null) {
					for (Content c : s.getContent()) {
						sb.append("\n" + c.getName());
					}
				}
				if(s.getStatus() == SportObjectStatus.Open) {
					retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), sb.toString(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"OPEN"));	
				}
				else {
					retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), sb.toString(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"CLOSE"));	
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
					if (cnt == 0) {
						StringBuilder sb = new StringBuilder("");
						if (s.getContent() != null) {
							for (Content c : s.getContent()) {
								sb.append("\n" + c.getName());
							}
						}
						retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), sb.toString(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"OPEN"));
					}
				}
				else {
					int cnt = 0;
					for (SportObjectDTO so : retVal) {
						if (so.getId().equals(s.getId()))
							cnt++;
					}
					if (cnt == 0) {
						StringBuilder sb = new StringBuilder("");
						if (s.getContent() != null) {
							for (Content c : s.getContent()) {
								sb.append("\n" + c.getName());
							}
						}
						retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), sb.toString(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"CLOSE"));	
					}
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
					if (cnt == 0) {
						StringBuilder sb = new StringBuilder("");
						if (s.getContent() != null) {
							for (Content c : s.getContent()) {
								sb.append("\n" + c.getName());
							}
						}
						retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), sb.toString(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"OPEN"));
					}
				}
				else {
					int cnt = 0;
					for (SportObjectDTO so : retVal) {
						if (so.getId().equals(s.getId()))
							cnt++;
					}
					if (cnt == 0) {
						StringBuilder sb = new StringBuilder("");
						if (s.getContent() != null) {
							for (Content c : s.getContent()) {
								sb.append("\n" + c.getName());
							}
						}
						retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), sb.toString(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"CLOSE"));
					}
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
					if (cnt == 0) {
						StringBuilder sb = new StringBuilder("");
						if (s.getContent() != null) {
							for (Content c : s.getContent()) {
								sb.append("\n" + c.getName());
							}
						}
						retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), sb.toString(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"OPEN"));
					}
				}
				else {
					int cnt = 0;
					for (SportObjectDTO so : retVal) {
						if (so.getId().equals(s.getId()))
							cnt++;
					}
					if (cnt == 0) {
						StringBuilder sb = new StringBuilder("");
						if (s.getContent() != null) {
							for (Content c : s.getContent()) {
								sb.append("\n" + c.getName());
							}
						}
						retVal.add(new SportObjectDTO(s.getId(), s.getName(), s.getType(), sb.toString(), s.getLocation().toString(), s.getAverageGrade(), s.getImage(), s.getWorkTime().toString(),"CLOSE"));	
					}
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
	public SportObjectDTO transformToDTO(IdDTO sportObjectId) {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		SportObjectDTO sportObjectDTO;
		SportObject sportObject = repo.read(sportObjectId.getId());
		StringBuilder sb = new StringBuilder("");
		if (sportObject.getContent() != null) {
			for (Content c : sportObject.getContent()) {
				sb.append("\n" + c.getName());
			}
		}
		sportObject.setStatus();
		if(sportObject.getStatus() == SportObjectStatus.Open) {
			sportObjectDTO = new SportObjectDTO(sportObject.getId(), sportObject.getName(), sportObject.getType(), sb.toString(), sportObject.getLocation().toString(), sportObject.getAverageGrade(), sportObject.getImage(), sportObject.getWorkTime().toString(),"OPEN");	
		}
		else {
			sportObjectDTO = new SportObjectDTO(sportObject.getId(), sportObject.getName(), sportObject.getType(), sb.toString(), sportObject.getLocation().toString(), sportObject.getAverageGrade(), sportObject.getImage(), sportObject.getWorkTime().toString(),"CLOSE");	
		}
		
		return sportObjectDTO;
	}
	
	@POST
	@Path("getCustomersFromSportObject")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<CustomerDTO> getCustomersFromSportObject(IdDTO sportObjectId) {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		ArrayList<CustomerDTO> retVal = new ArrayList<CustomerDTO>();
		for (Customer c : customerRepo.getAll()) {
			if (c.getVisitedObjects() != null) {
				for (SportObject s : c.getVisitedObjects()) {
					if (s.getId().equals(sportObjectId.getId())) {
						retVal.add(new CustomerDTO(c.getUsername(),c.getName(), c.getLastName(),c.getGender(),c.getDateOfBirth()));
					}
				}
			}
		}
		
		return retVal;
	}
	
	@POST
	@Path("addContent")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Content addContent(AddContentDTO contentDTO) {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		if (isContentUnique(contentDTO.getName())) {
			Content content = new Content(contentDTO.getName(), contentDTO.getType(), contentDTO.getImage(), contentDTO.getDescription(), contentDTO.getDurationInMinutes());
			SportObject sportObject = repo.read(contentDTO.getSportObjectId());
			ArrayList<Content> contents;
			if (sportObject.getContent() != null) {
				contents = sportObject.getContent();
			}
			else {
				contents = new ArrayList<Content>();
			}
			contents.add(content);
			sportObject.setContent(contents);
			repo.update(sportObject);			
			return content;
		}
		
		return null;
	}
	
	
	public boolean isContentUnique(String name) {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		boolean isUnique = true;
		ArrayList<SportObject> sportObjects = repo.getAll();
		for (SportObject s : sportObjects) {
			if (s.getContent() != null) {
				for (Content c : s.getContent()) {
					if (c.getName().equals(name)) {
						isUnique = false;
					}
				}
			}
		}
		
		return isUnique;	
	}
	
	
}