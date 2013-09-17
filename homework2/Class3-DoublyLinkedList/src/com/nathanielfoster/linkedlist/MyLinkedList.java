package com.nathanielfoster.linkedlist;

public class MyLinkedList {
	private Node backTraceNode = null;
	private Node firstNode = null;
	
	public void add(String input) {
		// TODO Auto-generated method stub
		Node tmpNode = new Node(input);
		
		if(backTraceNode!=null){
			backTraceNode.setNextNode(tmpNode);	
		} else {
			firstNode = tmpNode;
		}
		backTraceNode=tmpNode;
	}

	public String print() {
		Node tmpNode = firstNode;
		StringBuffer result = new StringBuffer();
		
		while(tmpNode!=null){	
			result.append(tmpNode.getValue() + " ");
			tmpNode = tmpNode.getNextNode();
		}
		return result.toString();
	}

}
