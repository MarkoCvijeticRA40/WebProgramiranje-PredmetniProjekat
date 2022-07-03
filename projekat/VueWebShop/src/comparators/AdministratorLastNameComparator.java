package comparators;

import java.util.Comparator;

import dto.AdministratorDTO;

public class AdministratorLastNameComparator implements Comparator<AdministratorDTO> {
		@Override
		public int compare(AdministratorDTO o1,AdministratorDTO o2) {
			return o1.lastName.compareTo(o2.lastName);
		}
}

