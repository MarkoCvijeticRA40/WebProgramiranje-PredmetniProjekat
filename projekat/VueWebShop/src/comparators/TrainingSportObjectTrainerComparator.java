package comparators;

import java.util.Comparator;

import model.Training;

public class TrainingSportObjectTrainerComparator implements Comparator<Training> {
		@Override
		public int compare(Training o1,Training o2) {
			return o1.getSportObject().getName().compareTo(o2.getSportObject().getName());
		}
}

