package comparators;

import java.util.Comparator;

import dto.CustomerDTO;

public class CustomerComparePoints implements Comparator<CustomerDTO> {
	@Override
	public int compare(CustomerDTO o1,CustomerDTO o2) {
		return Double.compare(o1.points, o2.points);
	}
}
