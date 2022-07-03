package comparators;

import java.util.Comparator;

import dto.ManagerDTO;

public class ManagerUserNameComparator implements Comparator<ManagerDTO>{
	@Override
	public int compare(ManagerDTO o1,ManagerDTO o2) {
		return o1.username.compareTo(o2.username);
	}
}
