package com.HAPPYTRIP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HappytripApplication {

	static class A{





		void print(){
			int a=1;
			int b=2;
			System.out.println(a+b);
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(HappytripApplication.class, args);


		A a=new A();
		a.print();


		System.out.println("수정이 바보");
		System.out.println("진원 뚱바보");

	}


}
