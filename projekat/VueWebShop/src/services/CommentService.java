package services;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.CommentDTO;
import model.Comment;
import model.Customer;
import model.IdGenerator;
import model.SportObject;
import repository.CommentRepository;
import repository.CustomerRepository;
import repository.SportObjectRepository;

@Path("comments")
public class CommentService {

CommentRepository repo = new CommentRepository();
CustomerRepository customerRepo = new CustomerRepository();
SportObjectRepository sportObjectRepo = new SportObjectRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("comments") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("comments", new CommentService());
		}
	}
	
	
	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addComment(CommentDTO commentDTO) {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		sportObjectRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		Customer customer = customerRepo.read(commentDTO.getCustomerId());
		SportObject sportObject = sportObjectRepo.read(commentDTO.getSportObjectId());
		
		Comment comment = new Comment(IdGenerator.getInstance().generateId(repo.getKeySet(), 10), customer, sportObject, commentDTO.getComment(), commentDTO.getGrade(), false);
		repo.create(comment);
		
	}
	
//	@GET
//	@Path("createAuto")	
//	@Produces(MediaType.TEXT_PLAIN)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void createAdministratorAuto()
//	{
//		repo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
//				
//		Address adr = new Address("Spens","Novi Sad","22000");
//		Location loc = new Location(101.5,55.9,adr);
//		WorkTime work = new WorkTime(LocalTime.of(15,0,0),LocalTime.of(23,0,0));
//		SportObject newCustomer = new SportObject("6","Spens","Hala","Grupni treninzi",loc, 4.9,"images" + File.separator + "spens.png", work);
//		Comment comment = new Comment("1", new CustomerType("Zlatni", 50, 1000), newCustomer, "Sve pohvale za ambijent! Sjajna atmosfera i osoblje.", 5);
//		Map<String, Comment> mapa = new HashMap<String, Comment>();
//		mapa.put(comment.getId(), comment);
//		repo.writeFile(mapa);
//		//repo.create(newCustomer);
//		//System.out.println("Created new customer: ");
//	}
//	
}
