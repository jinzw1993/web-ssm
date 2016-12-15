package com.ruitao.shop;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TestJava {
	
	@Test
    public void testList() {
    	List<String> list = new LinkedList<String>();
    	list.add("a");
    	list.add("bb");
    	list.add("ccc");
    	System.out.println(list.contains("d"));
    }
	
	@Test
	public void testLong() {
		long x = 2;
		long y = 3;
		System.out.println((long)x+y);
	}

}
