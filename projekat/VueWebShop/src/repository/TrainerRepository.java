package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.Trainer;

public class TrainerRepository extends GenericRepository<Trainer, TrainerRepository> {

	@Override
	protected String getFileName() {
		return "trainers.json";
	}

	@Override
	protected String getKey(Trainer e) {
		return e.getId();
	}
	
	public ArrayList<Trainer> getAll() {
		Map<String, Trainer> map = getMap();
		ArrayList<Trainer> list = new ArrayList<>();

		for (Map.Entry<String, Trainer> entry : map.entrySet()) {
			list.add(((Trainer) entry.getValue()));
		}

		return list;
	}

	public Map<String, Trainer> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath()))); //bilo getPath()
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Trainer>>() {
		}.getType();

		Map<String, Trainer> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;

	}
	
	public Trainer getTrainerByUsername(String username) {
		for (Trainer t : getAll()) {
			if (t.getUsername().equals(username))
				return t;
		}
		
		return null;
	}

}
