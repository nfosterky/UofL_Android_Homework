package com.nathanielfoster.linkedlist;

public class Node {
	private String value = "";
	private Node next = null;
	private Node prev = null;
	
	public Node(String input) {
		this.value = input;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getNextNode() {
		return next;
	}

	public void setNextNode(Node pointerNode) {
		this.next = pointerNode;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + ", pointerNode=" + next + "]";
	}

	public Node getPrevNode() {
		return prev;
	}

	public void setPrevNode(Node backPointerNode) {
		this.prev = backPointerNode;
	}
	
}
