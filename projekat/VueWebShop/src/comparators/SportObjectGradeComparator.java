package comparators;

import java.util.Comparator;

import dto.SportObjectDTO;

public class SportObjectGradeComparator implements Comparator<SportObjectDTO> {
		@Override
		public int compare(SportObjectDTO o1,SportObjectDTO o2) {
			return o1.averageGrade.compareTo(o2.averageGrade);
		}
}