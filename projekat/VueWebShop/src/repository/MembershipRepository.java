package repository;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import com.google.gson.reflect.TypeToken;
import model.Membership;

public class MembershipRepository extends GenericRepository<Membership, MembershipRepository> {

	@Override
	protected String getFileName() {
		return "membership.json";
	}

	@Override
	protected String getKey(Membership e) {
		return e.getId();
	}
	
	public ArrayList<Membership> getAll() {
		Map<String, Membership> map = getMap();
		ArrayList<Membership> list = new ArrayList<>();

		for (Map.Entry<String, Membership> entry : map.entrySet()) {
			list.add(((Membership) entry.getValue()));
		}
		return list;
	}
	
	public Map<String, Membership> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath()))); 
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Membership>>() {
		}.getType();

		Map<String, Membership> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;

	}
}