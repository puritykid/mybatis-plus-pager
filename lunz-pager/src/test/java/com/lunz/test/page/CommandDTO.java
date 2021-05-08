package com.lunz.test.page;

import java.io.Serializable;

/**
*
*@Title:CommandDTO
*@Package:com.lunz.uc.users.management.dto
*@Description:TODO
*@author:liyanru
*@date:2019年5月13日
*/
public class CommandDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String loginName;
    
    private String userName;
    
    private String roleName;
    
    private String organizationName;
    
    private String roleGroupName;
    
    private String userGroupName;
    
    private int loginTimes;
    
    private int enabled;
    
    private String tel;
    
    private String idCard;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getRoleGroupName() {
		return roleGroupName;
	}

	public void setRoleGroupName(String roleGroupName) {
		this.roleGroupName = roleGroupName;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public int getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}

