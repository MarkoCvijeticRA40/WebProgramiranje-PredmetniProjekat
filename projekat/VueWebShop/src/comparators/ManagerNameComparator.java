package comparators;

import java.util.Comparator;

import dto.ManagerDTO;

public class ManagerNameComparator implements Comparator<ManagerDTO>{
	@Override
	public int compare(ManagerDTO o1,ManagerDTO o2) {
		return o1.name.compareTo(o2.name);
	}
}

