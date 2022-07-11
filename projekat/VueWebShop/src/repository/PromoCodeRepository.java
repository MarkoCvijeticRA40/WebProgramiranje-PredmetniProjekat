package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.PromoCode;

public class PromoCodeRepository extends GenericRepository<PromoCode, PromoCodeRepository> {

	@Override
	protected String getFileName() {
		return "promoCodes.json";
	}

	@Override
	protected String getKey(PromoCode e) {
		return e.getId();
	}
	
	
	public ArrayList<PromoCode> getAll() {
		Map<String, PromoCode> map = getMap();
		ArrayList<PromoCode> list = new ArrayList<>();

		for (Map.Entry<String, PromoCode> entry : map.entrySet()) {
			if (!entry.getValue().isDeleted())
				list.add(((PromoCode) entry.getValue()));
		}

		return list;
	}

	public Map<String, PromoCode> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath()))); 
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, PromoCode>>() {
		}.getType();

		Map<String, PromoCode> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;

	}
	
	public PromoCode getById(String id) {
		PromoCode retVal = null;
		for(PromoCode p: getAll()) {
			if(p.getId().equals(id))
			{
				retVal = p;
			}
		}
		
		return retVal;
	}
	
	
	public void delete(String id) {
		Map<String, PromoCode> map = getMap();
		map.get(id).setDeleted(true);
		writeFile(map);
	}

}
