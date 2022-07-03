package comparators;

import java.util.Comparator;

import dto.TrainerDTO;

public class TrainerLastNameComparator implements Comparator<TrainerDTO>{
	@Override
	public int compare(TrainerDTO o1,TrainerDTO o2) {
		return o1.lastName.compareTo(o2.lastName);
	}
}