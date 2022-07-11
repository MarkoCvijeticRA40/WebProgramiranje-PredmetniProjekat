package comparators;

import java.util.Comparator;

import dto.TrainingDTO;
import model.HistoryOfAllTrainings;

public class TrainingSportObjectComparator implements Comparator<HistoryOfAllTrainings> {
		@Override
		public int compare(HistoryOfAllTrainings o1,HistoryOfAllTrainings o2) {
			return o1.getTraining().getSportObject().getName().compareTo(o2.getTraining().getSportObject().getName());
		}
}
