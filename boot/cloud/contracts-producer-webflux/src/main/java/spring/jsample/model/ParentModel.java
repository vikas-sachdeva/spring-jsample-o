package spring.jsample.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

public class ParentModel {

	@CreatedDate
	protected Date createdAt;

	@LastModifiedDate
	protected Date lastModifiedDate;
	
	public ParentModel() {
		
	}

	public ParentModel(Date createdAt, Date lastModifiedDate) {
		this.createdAt = createdAt;
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
