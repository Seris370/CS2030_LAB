import java.util.*;

class Main {
	static Scanner sc = new Scanner(System.in);

	static ArrayList<Integer> groups = new ArrayList<>();
	static ArrayList<Student> students = new ArrayList<>();
	static ArrayList<Mark> marks = new ArrayList<>();
	static ArrayList<Integer> scores = new ArrayList<>();
	static int min;
	static int max;

	static void printMark(ArrayList<Integer> list) {
		Collections.sort(list);

		System.out.printf("Mark frequency from %d to %d\n", min, max);
		for (int i = min; i <= max; i++) {
			int sum = 0;
			int j = 0;
			while (j < list.size()) {
				if (list.get(j) == i) {
					sum++;
					list.remove(j);
				} else {
					break;
				}
			}
			System.out.printf("%d : %d\n", i, sum);
		}
	}

	public static void main(String[] args) {
		// read students
		while (sc.hasNext()) {
			String str = sc.next();
			if (str.equals("end")) {
				break;
			} else {
				String id = sc.next();
				int group = sc.nextInt();
				if (!groups.contains(group)) {
					groups.add(group);
				}
				Student student = new Student(str, id, group);
				students.add(student);
			}
		}

		// read marks
		while (sc.hasNext()) {
			String str = sc.next();
			if (str.equals("end")) {
				break;
			} else {
				int i = sc.nextInt();
				Mark mark = new Mark(str, i);
				marks.add(mark);
				scores.add(i);
			}
		}

		// sort groups
		Collections.sort(groups);

		// print groups
		System.out.print("Groups(" + groups.size() + "):[");
		for (int i = 0; i < groups.size(); i++) {
			System.out.print(groups.get(i));
			if (i == groups.size() - 1) {
				System.out.println("]");
			} else {
				System.out.print(", ");
			}
		}

		// group marks
		ArrayList<ArrayList<Integer>> ss = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < groups.size(); i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (Student student : students) {
				if (student.getGroup() == groups.get(i)) {
					for (Mark mark : marks) {
						if (mark.getPlab().equals(student.getPlab())) {
							temp.add(mark.getMark());
						}
					}
				}
			}
			ss.add(i, temp);
		}

		// print students
		int i = 0;
		while (i < students.size()) {
			System.out.print(students.get(i) + ",");
			boolean flag = false;
			for (Mark mark : marks) {
				if (mark.getPlab().equals(students.get(i).getPlab())) {
					System.out.println(mark.getMark());
					flag = true;
					break;
				}
			}
			if (flag == true) {
				students.remove(i);
			} else {
				System.out.println(0);
				i++;
			}
		}

		// print absentees
		System.out.println("List of absentees:");
		if (students.size() == 0) {
			System.out.println("None");
		} else {
			for (Student student : students) {
				System.out.println(student);
			}
		}

		// print mark frequency
		Collections.sort(scores);
		int length = scores.size();
		min = scores.get(0);
		max = scores.get(length - 1);
		printMark(scores);
		for (int k = 0; k < ss.size(); k++) {
			if (ss.get(k).size() == 0){
				continue;
			}
			System.out.printf("Group #%d...", groups.get(k));
			printMark(ss.get(k));
		}
	}
}
