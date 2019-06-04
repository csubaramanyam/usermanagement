package com.synf.user.usermanagement.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_IMAGES")
public class UserImage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "USER_IMAGE_ID")
	private Integer userImageId;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "SRC_IMAGE_NAME")
	private String srcImageName;

	@Column(name = "IMGUR_IMAGE_ID")
	private String imgurImageId;

	@Column(name = "IMGUR_IMAGE_TYPE")
	private String imgurImageType;

	@Column(name = "IMGUR_IMAGE_TITLE")
	private String imgurImageTitle;

	@Column(name = "IMGUR_IMAGE_DESC")
	private String imgurImageDesc;

	@Column(name = "IMGUR_IMAGE_DEL_HASH")
	private String imgurImageDeleteHash;

	@Column(name = "IMGUR_IMAGE_LINK")
	private String imgurImageLink;

	@Column(name = "UPLOAD_DATE")
	private Date uploadedDate;

	public Integer getUserImageId() {
		return userImageId;
	}

	public void setUserImageId(Integer userImageId) {
		this.userImageId = userImageId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSrcImageName() {
		return srcImageName;
	}

	public void setSrcImageName(String srcImageName) {
		this.srcImageName = srcImageName;
	}

	public String getImgurImageId() {
		return imgurImageId;
	}

	public void setImgurImageId(String imgurImageId) {
		this.imgurImageId = imgurImageId;
	}

	public String getImgurImageType() {
		return imgurImageType;
	}

	public void setImgurImageType(String imgurImageType) {
		this.imgurImageType = imgurImageType;
	}

	public String getImgurImageTitle() {
		return imgurImageTitle;
	}

	public void setImgurImageTitle(String imgurImageTitle) {
		this.imgurImageTitle = imgurImageTitle;
	}

	public String getImgurImageDesc() {
		return imgurImageDesc;
	}

	public void setImgurImageDesc(String imgurImageDesc) {
		this.imgurImageDesc = imgurImageDesc;
	}

	public String getImgurImageDeleteHash() {
		return imgurImageDeleteHash;
	}

	public void setImgurImageDeleteHash(String imgurImageDeleteHash) {
		this.imgurImageDeleteHash = imgurImageDeleteHash;
	}

	public String getImgurImageLink() {
		return imgurImageLink;
	}

	public void setImgurImageLink(String imgurImageLink) {
		this.imgurImageLink = imgurImageLink;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	@Override
	public String toString() {
		return "UserImage [userImageId=" + userImageId + ", userId=" + userId + ", srcImageName=" + srcImageName
				+ ", imgurImageId=" + imgurImageId + ", imgurImageType=" + imgurImageType + ", imgurImageTitle="
				+ imgurImageTitle + ", imgurImageDesc=" + imgurImageDesc + ", imgurImageDeleteHash="
				+ imgurImageDeleteHash + ", imgurImageLink=" + imgurImageLink + ", uploadedDate=" + uploadedDate + "]";
	}

}
