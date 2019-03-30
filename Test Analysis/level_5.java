import java.util.*;

class Main {
	static Scanner sc = new Scanner(System.in);

	static ArrayList<Integer> groups = new ArrayList<>();
	static ArrayList<Student> students = new ArrayList<>();
	static ArrayList<Mark> marks = new ArrayList<>();
	static ArrayList<Integer> scores = new ArrayList<>();

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
		int min = scores.get(0);
		int max = scores.get(length - 1);

		System.out.printf("Mark frequency from %d to %d\n", scores.get(0), scores.get(length - 1));
		for (i = min; i <= max; i++) {
			int sum = 0;
			int j = 0;
			while (j < scores.size()) {
				if (scores.get(j) == i) {
					sum++;
					scores.remove(j);
				} else {
					break;
				}
			}
			System.out.printf("%d : %d\n", i, sum);
		}
	}
}
