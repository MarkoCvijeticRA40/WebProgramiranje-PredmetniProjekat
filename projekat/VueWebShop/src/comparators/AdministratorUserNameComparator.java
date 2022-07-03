package comparators;

import java.util.Comparator;

import dto.AdministratorDTO;

public class AdministratorUserNameComparator implements Comparator<AdministratorDTO> {
	@Override
	public int compare(AdministratorDTO o1,AdministratorDTO o2) {
		return o1.username.compareTo(o2.username);
	}
}



