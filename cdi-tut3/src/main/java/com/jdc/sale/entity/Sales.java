package com.jdc.sale.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@NamedQuery(name = "Sales.getAll", query = "select s from Sales s")
public class Sales implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "Please enter your name.")
	private String customer;
	@NotEmpty(message = "Please enter your phone.")
	private String phone;
	@Email(message = "Invalid Email Address.")
	private String email;
	private LocalDateTime saleTime;
	
	@OneToMany(mappedBy = "sales", fetch = FetchType.EAGER)
	private List<SalesDetails> orders;
	
	@PrePersist
	private void prePersist() {
		saleTime = LocalDateTime.now();
	}
	
	public int getTotal() {
		return orders.stream().mapToInt(od -> od.getProduct().getPrice() * od.getQuentity()).sum();
	}
	
	public int getQuentity() {
		return orders.stream().mapToInt(od -> od.getQuentity()).sum();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(LocalDateTime saleTime) {
		this.saleTime = saleTime;
	}

	public List<SalesDetails> getOrders() {
		return orders;
	}

	public void setOrders(List<SalesDetails> orders) {
		this.orders = orders;
	}

	
}
