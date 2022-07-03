package comparators;

import java.util.Comparator;

import dto.ManagerDTO;

public class ManagerLastNameComparator implements Comparator<ManagerDTO>{
	@Override
	public int compare(ManagerDTO o1,ManagerDTO o2) {
		return o1.lastName.compareTo(o2.lastName);
	}
}
