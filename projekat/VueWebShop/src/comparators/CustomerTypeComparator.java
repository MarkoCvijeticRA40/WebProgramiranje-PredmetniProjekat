package comparators;

import java.util.Comparator;

import dto.CustomerDTO;

public class CustomerTypeComparator implements Comparator<CustomerDTO> {
		@Override
		public int compare(CustomerDTO o1,CustomerDTO o2) {
			return o1.customerType.name.compareTo(o2.customerType.name);
		}
		
}	