package comparators;

import java.util.Comparator;

import dto.TrainerDTO;

public class TrainerUserNameComparator implements Comparator<TrainerDTO>{
	@Override
	public int compare(TrainerDTO o1,TrainerDTO o2) {
		return o1.username.compareTo(o2.username);
	}
}