package com.synf.user.usermanagement.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UserImageDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userImageId;

	private Integer userId;

	private String srcImageName;

	private String imgurImageId;

	private String imgurImageType;

	private String imgurImageTitle;

	private String imgurImageDesc;

	private String imgurImageDeleteHash;

	private String imgurImageLink;

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
		return "UserImageDTO [userImageId=" + userImageId + ", userId=" + userId + ", srcImageName=" + srcImageName
				+ ", imgurImageId=" + imgurImageId + ", imgurImageType=" + imgurImageType + ", imgurImageTitle="
				+ imgurImageTitle + ", imgurImageDesc=" + imgurImageDesc + ", imgurImageDeleteHash="
				+ imgurImageDeleteHash + ", imgurImageLink=" + imgurImageLink + ", uploadedDate=" + uploadedDate + "]";
	}

}
