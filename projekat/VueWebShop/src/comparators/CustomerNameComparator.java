package comparators;

import java.util.Comparator;

import dto.CustomerDTO;

public class CustomerNameComparator implements Comparator<CustomerDTO> {
	@Override
	public int compare(CustomerDTO o1,CustomerDTO o2) {
		return o1.name.compareTo(o2.name);
	}
}
