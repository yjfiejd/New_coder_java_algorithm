// 比较器的构造：
// 一个系统提供的有序的结构，都会伴随着比较器的构造，这个比较器告诉这个有序结构如何组织!! -> 可以用排序，优先级队列，红黑树
// 基于比较的排序 

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import Code_09_Comparator.Student;

public class comparator {

	// 主函数
	public static void main(String[] args) {
		Student student1 = new Student("A", 1, 23);
		Student student2 = new Student("B", 2, 21);
		Student student3 = new Student("C", 3, 22);
		
		Student[] students = new Student[] {student3, student2, student1};
		printStudent(students);
		
		Arrays.sort(students, new IdAscendingComparator());
		printStudent(students);
		
		Arrays.sort(students, new IdDescendingComparator());
		printStudent(students);
		
		// 堆的方式:
		// 系统提供的一个堆（优先级队列）	// 如果不给比较器，它默认按内存地址排序，没有意思, 需要仍进去比较器
		// 这里如果扔进去，id小的排在前面，则这是一个小根堆， -> 这里通过比较器构成了小根堆
		System.out.println("*********************************************");
		PriorityQueue<Student> heap = new PriorityQueue<>(new IdAscendingComparator());
		heap.add(student3);
		heap.add(student2);
		heap.add(student1);
		
		while (!heap.isEmpty()) {
			Student student = heap.poll(); // 弹出堆
			System.out.println("name: " + student.name +" "+ "id: " + student.id + " "+"Age: " + student.age );
		}
	}
	
	
	// 1) 创建Student类， 内部包含3个变量，名字，id，年龄
	public static class Student{
		public String name;
		public int id;
		public int age;
		
		public Student(String name, int id, int age) {
			this.name = name;
			this.id = id;
			this.age = age;
		}
	}

	// 2) 创建一个比较器类 -> id升序，继承
	public static class IdAscendingComparator implements Comparator<Student>{
		@Override
		public int compare(Student o1, Student o2) {
//			return o1.id - o2.id;
			if(o1.id < o2.id) {
				return -1; // 表示o1更小， 因为o1.id - o2.id < 0 , 为负数；第一个参数放前面。
			}else if (o1.id > o2.id) {
				return 1; // 表示o1更大， 因为o1.id - o2.id > 0 , 为正数；
			}else {
				return 0;
			}
		}
	}
	
	// 比较器 -> 年龄降序
	public static class IdDescendingComparator implements Comparator<Student>{
		@Override
		public int compare(Student o1, Student o2) {
			return o2.age - o1.age;  // 浓缩的写法，如果o2.age - o1.age > 0, 所有o2更大。
		}
	}
	
	
	// 3) 打印函数如图
	public static void printStudent(Student[] students) {
		for (Student student:students) {
			System.out.println("name: " + student.name +" "+ "id: " + student.id + " "+"Age: " + student.age );
		}
		System.out.println("============================================");
	}
	
}
