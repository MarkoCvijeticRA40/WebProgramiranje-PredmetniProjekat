package services;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import comparators.AdministratorLastNameComparator;
import comparators.AdministratorNameComparator;
import comparators.AdministratorUserNameComparator;
import dto.AdministratorDTO;
import dto.SearchDTO;
import model.Administrator;
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
	
	@GET
	@Path("getAll")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<AdministratorDTO> getAll() {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Administrator> administrators = repo.getAll();
		ArrayList<AdministratorDTO> retVal = new ArrayList<AdministratorDTO>();
		for (Administrator s : administrators) {			
			retVal.add(new AdministratorDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth()));	
		}
		return retVal;
	}
	
	@POST
	@Path("update")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Administrator updateAdministrator(Administrator administrator) {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		repo.update(administrator);
		return administrator;
	}
	
	@POST
	@Path("search")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<AdministratorDTO> search(SearchDTO search) {
		if (search.getSearchText().isBlank()) {
			return getAll();
		}
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<AdministratorDTO> retVal = new ArrayList<AdministratorDTO>();
		ArrayList<Administrator> administrators = repo.getAll();
		
		for (Administrator s : administrators) {
			if (s.getName().toLowerCase().trim().contains(search.getSearchText().toLowerCase().trim())) {	
					retVal.add(new AdministratorDTO(s.getUsername(),s.getName(),s.getLastName(),s.getGender(),s.getDateOfBirth()));	
			}
		}
	    
		for (Administrator s : administrators) {
			if (s.getLastName().toLowerCase().trim().contains(search.getSearchText().toLowerCase().trim())) {	
					int cnt = 0;
					for (AdministratorDTO so : retVal) {
						if (so.getUsername().equals(s.getUsername()))
							cnt++;
					}
					if (cnt == 0)
						retVal.add(new AdministratorDTO(s.getUsername(),s.getName(),s.getLastName(),s.getGender(),s.getDateOfBirth()));	
			}
		}

		for (Administrator s : administrators) {
			if (s.getUsername().toLowerCase().trim().contains(search.getSearchText().toLowerCase().trim())) {	
					int cnt = 0;
					for (AdministratorDTO so : retVal) {
						if (so.getUsername().equals(s.getUsername()))
							cnt++;
					}
					if (cnt == 0)
						retVal.add(new AdministratorDTO(s.getUsername(),s.getName(),s.getLastName(),s.getGender(),s.getDateOfBirth()));	
			}
		}	
		return retVal;
	}
	
	public ArrayList<AdministratorDTO> nameACS(ArrayList<AdministratorDTO> retVal) {
		Collections.sort(retVal, new AdministratorNameComparator());
		return retVal;
	}
	
	public ArrayList<AdministratorDTO> nameDESC(ArrayList<AdministratorDTO> retVal) {
		Collections.sort(retVal, new AdministratorNameComparator());
		Collections.reverse(retVal);
		return retVal;
	}
	
	public ArrayList<AdministratorDTO> lastNameACS(ArrayList<AdministratorDTO> retVal) {
		Collections.sort(retVal, new AdministratorLastNameComparator());
		return retVal;
	}
	
	public ArrayList<AdministratorDTO> lastNameDESC(ArrayList<AdministratorDTO> retVal) {
		Collections.sort(retVal, new AdministratorLastNameComparator());
		Collections.reverse(retVal);
		return retVal;
	}
	
	private ArrayList<AdministratorDTO> userNameACS(ArrayList<AdministratorDTO> retVal) {
		Collections.sort(retVal, new AdministratorUserNameComparator());
		return retVal;
	}
	
	private ArrayList<AdministratorDTO> userNameDESC(ArrayList<AdministratorDTO> retVal) {
		Collections.sort(retVal, new AdministratorUserNameComparator());
		Collections.reverse(retVal);
		return retVal;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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