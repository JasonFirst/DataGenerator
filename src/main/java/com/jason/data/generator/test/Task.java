package com.jason.data.generator.test;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Task {
	
	private String title;
	private Integer price;
	private Date endDate;
	private Set<String> nameSet;
	private LinkedHashSet<String> channelNames;
	private List<SubTask> subTasks;
	private LinkedList<String> linkedList;
	private Map<Date, Long> mapTest;
	private TreeMap<String, Integer> keyToDate;
	private Boolean isOnGoing;
	private String contactWechat;
	private String timeString;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Set<String> getNameSet() {
		return nameSet;
	}
	public void setNameSet(Set<String> nameSet) {
		this.nameSet = nameSet;
	}
	public List<SubTask> getSubTasks() {
		return subTasks;
	}
	public void setSubTasks(List<SubTask> subTasks) {
		this.subTasks = subTasks;
	}
	public Boolean getIsOnGoing() {
		return isOnGoing;
	}
	public void setIsOnGoing(Boolean isOnGoing) {
		this.isOnGoing = isOnGoing;
	}
	public String getContactWechat() {
		return contactWechat;
	}
	public void setContactWechat(String contactWechat) {
		this.contactWechat = contactWechat;
	}
	public String getTimeString() {
		return timeString;
	}
	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	public Map<String, Integer> getKeyToDate() {
		return keyToDate;
	}
	public LinkedHashSet<String> getChannelNames() {
		return channelNames;
	}
	public void setChannelNames(LinkedHashSet<String> channelNames) {
		this.channelNames = channelNames;
	}
	public void setKeyToDate(TreeMap<String, Integer> keyToDate) {
		this.keyToDate = keyToDate;
	}
	public Map<Date, Long> getMapTest() {
		return mapTest;
	}
	public void setMapTest(Map<Date, Long> mapTest) {
		this.mapTest = mapTest;
	}
	public LinkedList<String> getLinkedList() {
		return linkedList;
	}
	public void setLinkedList(LinkedList<String> linkedList) {
		this.linkedList = linkedList;
	}
	
}
