package com.irisking.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;


@Entity
@Table(name="user",
	indexes = {
        @Index(columnList = "name", name = "name_index")}
)
public class UserOrm {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    
    @Column(name="name", unique=true)
    
    public String name;
    
    @Column(name="pwd")
    @NotNull
    public String pwd;

    /**/
    @Column(name="token", columnDefinition="varchar(128) default''")
    public String token;
    
    /*最后登录的时间戳*/
    @Column(name="timeStamp", columnDefinition="int(64) default 0")
    public Long timeStamp;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
    
}
