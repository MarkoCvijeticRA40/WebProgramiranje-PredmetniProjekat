package comparators;

import java.util.Comparator;

import dto.SportObjectDTO;

public class SportObjectNameComparator implements Comparator<SportObjectDTO> {

	@Override
	public int compare(SportObjectDTO o1, SportObjectDTO o2) {
		return o1.name.compareTo(o2.name);
	}

}