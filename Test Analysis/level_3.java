import java.util.*;

class Main {
	static Scanner sc = new Scanner(System.in);

	static ArrayList<Integer> groups = new ArrayList<>();
	static ArrayList<Student> students = new ArrayList<>();
	static ArrayList<Mark> marks = new ArrayList<>();

	public static void main(String[] args) {
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

		while (sc.hasNext()) {
			String str = sc.next();
			if (str.equals("end")) {
				break;
			} else {
				int i = sc.nextInt();
				Mark mark = new Mark(str, i);
				marks.add(mark);
			}
		}

		Collections.sort(groups);

		System.out.print("Groups(" + groups.size() + "):[");
		for (int i = 0; i < groups.size(); i++) {
			System.out.print(groups.get(i));
			if (i == groups.size() - 1) {
				System.out.println("]");
			} else {
				System.out.print(", ");
			}
		}

		for (Student student : students) {
			System.out.print(student + ",");
			boolean flag = false;
			for (Mark mark : marks) {
				if (mark.getPlab().equals(student.getPlab())) {
					System.out.println(mark.getMark());
					flag = true;
					break;
				}
			}
			if (flag == false) {
				System.out.println(0);
			}
		}
	}
}
