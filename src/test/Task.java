package test;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Task {
	
	private String title;
	private Integer price;
	private Date endDate;
	private List<String> channelNames;
	private Set<String> nameSet;
	private Map<String, Integer> keyToDate;
	private List<SubTask> subTasks;
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
	public List<String> getChannelNames() {
		return channelNames;
	}
	public void setChannelNames(List<String> channelNames) {
		this.channelNames = channelNames;
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
	public void setKeyToDate(Map<String, Integer> keyToDate) {
		this.keyToDate = keyToDate;
	}
	
}
