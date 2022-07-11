package services;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.AddPromoCodeDTO;
import dto.IdDTO;
import dto.UsernameDTO;
import model.Customer;
import model.IdGenerator;
import model.PromoCode;
import repository.PromoCodeRepository;

@Path("promocodes")
public class PromoCodeService {
	
	PromoCodeRepository repo = new PromoCodeRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("promoCodes") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("promoCodes", new PromoCodeService());
		}
	}
	
	
	@POST
	@Path("create")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PromoCode createPromoCode(AddPromoCodeDTO promoCodeDTO) {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		int startYear = Integer.parseInt(promoCodeDTO.getStartDate().split("-")[0]);
		int startMonth = Integer.parseInt(promoCodeDTO.getStartDate().split("-")[1]);
		int startDay = Integer.parseInt(promoCodeDTO.getStartDate().split("-")[2]);
		
		LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
		
		int endYear = Integer.parseInt(promoCodeDTO.getEndDate().split("-")[0]);
		int endMonth = Integer.parseInt(promoCodeDTO.getEndDate().split("-")[1]);
		int endDay = Integer.parseInt(promoCodeDTO.getEndDate().split("-")[2]);
		
		LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
		
		PromoCode promoCode = new PromoCode(IdGenerator.getInstance().generateId(repo.getKeySet(), 7), startDate, endDate, promoCodeDTO.getQuantity(), promoCodeDTO.getDiscountPercentage());
		repo.create(promoCode);
		
		return promoCode;
		
	}
	
	
	@GET
	@Path("getAll")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<PromoCode> getAll() {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		return repo.getAll();
	}

	
	@POST
	@Path("deletePromoCode")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteCustomer(IdDTO promoCodeId) {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		repo.delete(promoCodeId.getId());
	}
}
