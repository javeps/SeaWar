package com.sea;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DemoEntity {

	public static void main(String[] args) {
		List<A> list = new CopyOnWriteArrayList<A>();
		A p1 = new A("001", 1, 1);

		A p2 = new A("002", 2, 2);

		A p3 = new A("0013", 3, 3);

		list.add(p1);
		list.add(p2);
		list.add(p3);

		p1.age = 5;
		p2.money = 6;
		p3.name = "004";

		List<A> list2 = new CopyOnWriteArrayList<A>();
		list2.add(p1);
		list2.add(p2);
		list2.add(p3);

		p3.name = "0154";
		
		System.out.println(list.contains(p1) + "," + list2.contains(p1));
		System.out.println(list.contains(p2) + "," + list2.contains(p2));
		System.out.println(list.contains(p3) + "," + list2.contains(p3));
		
		for (A a : list) {
			System.out.println(a.toString());
		}

		for (A a : list2) {
			System.out.println(a.toString());
		}
	}

}

class A {
	public String name;
	public int age;
	public int money;

	public A(String name, int age, int money) {
		super();
		this.name = name;
		this.age = age;
		this.money = money;
	}

	@Override
	public String toString() {
		return "A [name=" + name + ", age=" + age + ", money=" + money + "]";
	}

}
