package com.jpmorgan.vivek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestComparable {

	public static void main(String[] argv) {
		Student s1 = new Student(5, "Vivek", 10);
		Student s2 = new Student(6, "Vinod", 9);

		List<Student> al = new ArrayList<Student>();
		al.add(new Student(101, "Vijay", 23));
		al.add(new Student(106, "Ajay", 27));
		al.add(new Student(105, "Jai", 21));
		al.add(s1);
		al.add(s2);

		Collections.sort(al);
		System.out.println(al.toString());
	}
}

class Student implements Comparable<Student> {
	int rollno;
	String name;
	int age;

	Student(int rollno, String name, int age) {
		this.rollno = rollno;
		this.name = name;
		this.age = age;
	}

	@Override
	public int compareTo(Student o) {
		if (o.age == this.age) {
			return 0;
		} else if (this.age > o.age) {
			return 1;
		} else
			return -1;
	}

	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", name=" + name + ", age=" + age + "]\n";
	}

}
