import java.util.*;

class Main {
	static Scanner sc = new Scanner(System.in);

	static ArrayList<Integer> groups = new ArrayList<>();
	static ArrayList<Student> students = new ArrayList<>();

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
			System.out.println(student);
		}
	}
}
