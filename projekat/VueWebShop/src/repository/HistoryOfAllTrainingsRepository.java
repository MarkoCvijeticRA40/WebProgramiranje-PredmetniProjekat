package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.HistoryOfAllTrainings;

public class HistoryOfAllTrainingsRepository extends GenericRepository<HistoryOfAllTrainings, HistoryOfAllTrainingsRepository> {

	@Override
	protected String getFileName() {
		return "trainingHistory.json";
	}

	@Override
	protected String getKey(HistoryOfAllTrainings e) {
		return e.getId();
	}
	
	public ArrayList<HistoryOfAllTrainings> getAll() {
		Map<String, HistoryOfAllTrainings> map = getMap();
		ArrayList<HistoryOfAllTrainings> list = new ArrayList<>();

		for (Map.Entry<String, HistoryOfAllTrainings> entry : map.entrySet()) {
			list.add(((HistoryOfAllTrainings) entry.getValue()));
		}

		return list;
	}

	public Map<String, HistoryOfAllTrainings> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath()))); 
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, HistoryOfAllTrainings>>() {
		}.getType();

		Map<String, HistoryOfAllTrainings> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;

	}

}
