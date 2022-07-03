package comparators;

import java.util.Comparator;

import dto.TrainerDTO;

public class TrainerNameComparator implements Comparator<TrainerDTO>{
	@Override
	public int compare(TrainerDTO o1,TrainerDTO o2) {
		return o1.name.compareTo(o2.name);
	}
}
