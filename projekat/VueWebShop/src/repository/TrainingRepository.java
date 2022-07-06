package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.Training;

public class TrainingRepository extends GenericRepository<Training, TrainingRepository> {

	@Override
	protected String getFileName() {
		return "trainings.json";
	}

	@Override
	protected String getKey(Training e) {
		return e.getId();
	}
	
	public ArrayList<Training> getAll() {
		Map<String, Training> map = getMap();
		ArrayList<Training> list = new ArrayList<>();

		for (Map.Entry<String, Training> entry : map.entrySet()) {
			list.add(((Training) entry.getValue()));
		}

		return list;
	}

	public Map<String, Training> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath()))); 
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Training>>() {
		}.getType();

		Map<String, Training> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;

	}
	
	
	public Training getTrainingByNameTypeAndSportObject(String name, String type, String sportObject) {
		for (Training t : getAll()) {
			if (t.getName().equals(name) && t.getType().equals(type) && t.getSportObject().getName().equals(sportObject)) {
				return t;
			}
		}
		
		return null;
	}

}
