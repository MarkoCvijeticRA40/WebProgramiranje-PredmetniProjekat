package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import com.google.gson.reflect.TypeToken;
import model.SportObject;

public class SportObjectRepository extends GenericRepository<SportObject, SportObjectRepository> {

	@Override
	protected String getFileName() {
		return "sportObject.json";
	}

	@Override
	protected String getKey(SportObject e) {
		return e.getId();
	}
	
	public ArrayList<SportObject> getAll() {
		Map<String, SportObject> map = getMap();
		ArrayList<SportObject> list = new ArrayList<>();

		for (Map.Entry<String, SportObject> entry : map.entrySet()) {
			list.add(((SportObject) entry.getValue()));
		}

		return list;
	}

	public Map<String, SportObject> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath()))); //bilo getPath()
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, SportObject>>() {
		}.getType();

		Map<String, SportObject> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;
	}
}