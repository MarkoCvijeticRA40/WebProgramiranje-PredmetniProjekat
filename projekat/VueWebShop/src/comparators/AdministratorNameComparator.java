package comparators;

import java.util.Comparator;

import dto.AdministratorDTO;

public class AdministratorNameComparator implements Comparator<AdministratorDTO> {
	@Override
	public int compare(AdministratorDTO o1,AdministratorDTO o2) {
		return o1.name.compareTo(o2.name);
	}
}

