package comparators;

import java.util.Comparator;

import dto.SportObjectDTO;

public class SportObjectLocationComparator implements Comparator<SportObjectDTO> {
	
	@Override
	public int compare(SportObjectDTO o1,SportObjectDTO o2) {
		return o1.addressName.compareTo(o2.addressName);
	}
		
}

