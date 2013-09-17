package com.nathanielfoster.linkedlist;

import junit.framework.Assert;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test() {
String names2Evaluate = "wes kim justin leanne ";
		
		DoublyLinkedList dList = new DoublyLinkedList();
		
		dList.add("leanne");
		dList.add("justin");
		dList.add("kim");
		dList.add("wes");
		
		Assert.assertEquals(names2Evaluate, dList.print());
		
	}
}
