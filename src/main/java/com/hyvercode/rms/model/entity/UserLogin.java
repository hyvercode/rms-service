package com.hyvercode.rms.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.hyvercode.rms.helper.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="user_login")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLogin extends BaseEntity {
	@Id
	@Column(name="login_id",length = 36,nullable = false)
	private String loginId;

	@Column(name="username",length = 30,nullable = false)
	private String username;

	@Column(name="password",length = 255,nullable = false)
	@JsonIgnore
	private String password;

	@Column(name = "outlet_id",length = 36)
	private String outletId;

	@Column(name = "employee_id",length = 36)
	private String employeeId;

	@Column(name = "active",length = 1,nullable = false)
	private String active;

}
