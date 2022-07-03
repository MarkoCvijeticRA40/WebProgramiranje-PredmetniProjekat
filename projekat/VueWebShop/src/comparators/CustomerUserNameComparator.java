package comparators;

import java.util.Comparator;

import dto.CustomerDTO;

public class CustomerUserNameComparator implements Comparator<CustomerDTO> {
	@Override
	public int compare(CustomerDTO o1,CustomerDTO o2) {
		return o1.username.compareTo(o2.username);
	}
}
