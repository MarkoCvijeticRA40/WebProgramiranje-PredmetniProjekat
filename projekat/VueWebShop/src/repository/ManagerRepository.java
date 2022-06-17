package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.Manager;

public class ManagerRepository extends GenericRepository<Manager, ManagerRepository> {

	@Override
	protected String getFileName() {
		return "managers.json";
	}

	@Override
	protected String getKey(Manager e) {
		return e.getId();
	}
	
	public ArrayList<Manager> getAll() {
		Map<String, Manager> map = getMap();
		ArrayList<Manager> list = new ArrayList<>();

		for (Map.Entry<String, Manager> entry : map.entrySet()) {
			list.add(((Manager) entry.getValue()));
		}

		return list;
	}

	public Map<String, Manager> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath()))); //bilo getPath()
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Manager>>() {
		}.getType();

		Map<String, Manager> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;

	}

}
