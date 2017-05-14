package test;

import java.util.Date;
import java.util.List;

public class SubTask {
	
	private String title;
	private Integer price;
	private Date beginDate;
	private Date endDate;
	private List<String> channelNames;
	
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
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
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
	
	
}
