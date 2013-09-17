package com.nathanielfoster.linkedlist;

public class DoublyLinkedList {
	private Node backTraceNode = null;
	private Node firstNode = null;
	
	public void add(String input) {
		// TODO Auto-generated method stub
		Node tmpNode = new Node(input);
		tmpNode.setPrevNode(backTraceNode);
		if(backTraceNode!=null){
			backTraceNode.setNextNode(tmpNode);
		} else {
			firstNode = tmpNode;
		}
		backTraceNode=tmpNode;
	}

	public String print() {
		Node tmpNode = backTraceNode;
		StringBuffer result = new StringBuffer();
		
		while(tmpNode!=null){	
			result.append(tmpNode.getValue() + " ");
			tmpNode = tmpNode.getPrevNode();
		}
		System.out.print(result.toString());
		return result.toString();
	}

}
