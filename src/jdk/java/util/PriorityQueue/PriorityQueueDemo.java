package jdk.java.util.PriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 优先队列示例
 *
 * @author Alan Yin
 * @date 2020/8/7
 */

public class PriorityQueueDemo {

    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(2);
        queue.add(1);
        queue.add(3);
        while (!queue.isEmpty()) {
            Integer i = queue.poll();
            System.out.println(i);
        }

        System.out.println("-----------------------");
        Comparator<Student> comparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (o1.id - o2.id);
            }
        };

        Queue<Student> queue2 = new PriorityQueue<>(comparator);
        queue2.add(new Student(2, "B"));
        queue2.add(new Student(1, "A"));
        queue2.add(new Student(3, "C"));

        while (!queue2.isEmpty()) {
            Student student = queue2.poll();
            System.out.println(student.toString());
        }
    }

    public static class Student {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return id + "-" + name;
        }
    }
}
