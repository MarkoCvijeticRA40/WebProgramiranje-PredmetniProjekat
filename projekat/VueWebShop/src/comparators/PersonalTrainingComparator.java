package comparators;

import java.util.Comparator;

import dto.PersonalTrainingDTO;

public class PersonalTrainingComparator implements Comparator<PersonalTrainingDTO> {
	@Override
	public int compare(PersonalTrainingDTO o1,PersonalTrainingDTO o2) {
		return o1.getName().compareTo(o2.getName());
	}
	
}
