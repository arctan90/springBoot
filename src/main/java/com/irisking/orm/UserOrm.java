package com.irisking.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


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
    
}
