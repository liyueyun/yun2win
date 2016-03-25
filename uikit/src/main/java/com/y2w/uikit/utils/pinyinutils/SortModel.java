package com.y2w.uikit.utils.pinyinutils;


import java.io.Serializable;

public class SortModel implements Serializable{

	/**
	 * 拼音排序模型
	 */
	private static final long serialVersionUID = 937392615586093884L;

	private String id;
	private String userId;
	private String name;
	private String sortLetters;  //显示数据拼音的首字母
	private String selectedStatus;
	private String avatarUrl;
	private int image;
	private String status;
	private String type;
	private String pinyin;
	private String email;
	private String role;
	private boolean isChoice =false;//加群选择人
	private boolean isMember = false;//是否已经是成员

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	public String getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(String selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isChoice() {
		return isChoice;
	}

	public void setIsChoice(boolean isChoice) {
		this.isChoice = isChoice;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isMember() {
		return isMember;
	}

	public void setIsMember(boolean isMember) {
		this.isMember = isMember;
	}
}
