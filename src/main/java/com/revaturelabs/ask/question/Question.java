package com.revaturelabs.ask.question;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {

		@Id
		private Integer id;
		
		@Column
		private Integer userId;
		
		@Column
		private String header;
		
		@Column 
		private String body;
		
		@Column
		private Date timestamp;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getHeader() {
			return header;
		}

		public void setHeader(String header) {
			this.header = header;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public Date getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}

		@Override
		public String toString() {
			return "Question [id=" + id + ", userId=" + userId + ", header=" + header + ", body=" + body
					+ ", timestamp=" + timestamp + "]";
		}
	
		
		
}
