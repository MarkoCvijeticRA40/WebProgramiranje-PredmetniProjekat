package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.Administrator;


public class AdministratorRepository  extends GenericRepository<Administrator, AdministratorRepository> {

	@Override
	protected String getFileName() {
		return "administrators.json";
	}

	@Override
	protected String getKey(Administrator e) {
		return e.getId();
	}
	
	public ArrayList<Administrator> getAll() {
		Map<String, Administrator> map = getMap();
		ArrayList<Administrator> list = new ArrayList<>();

		for (Map.Entry<String, Administrator> entry : map.entrySet()) {
			list.add(((Administrator) entry.getValue()));
		}

		return list;
	}

	public Map<String, Administrator> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath()))); //bilo getPath()
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Administrator>>() {
		}.getType();

		Map<String, Administrator> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;

	}

}
