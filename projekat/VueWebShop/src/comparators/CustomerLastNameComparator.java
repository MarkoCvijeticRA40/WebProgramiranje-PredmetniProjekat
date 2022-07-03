package comparators;

import java.util.Comparator;

import dto.CustomerDTO;

public class CustomerLastNameComparator implements Comparator<CustomerDTO> {
		@Override
		public int compare(CustomerDTO o1,CustomerDTO o2) {
			return o1.lastName.compareTo(o2.lastName);
		}
}
