package org.cgee.aquarius.model;

import java.util.List;

public class CooccurrenceListForWS {
	private String name;
	private int amount;
	private List<CooccurrenceVO>  children;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CooccurrenceVO> getChildren() {
		return children;
	}
	public void setChildren(List<CooccurrenceVO> children) {
		this.children = children;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
