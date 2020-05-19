package com.gitfocus.db.ui.model; 

import java.io.Serializable;

/**
 * @author Tech Mahindra 
 * A class for showing pull master commit details and count based on date
 * 
 */
public class PullMasterCommitDetailOnDate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String user = null;
	private String repoName = null;
	private String merged = null;
	private String pullStatus = null;
	private String fromBranch = null;
	private String createdTime = null;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRepoName() {
		return repoName;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public String getMerged() {
		return merged;
	}

	public void setMerged(String merged) {
		this.merged = merged;
	}

	public String getPullStatus() {
		return pullStatus;
	}

	public void setPullStatus(String pullStatus) {
		this.pullStatus = pullStatus;
	}

	public String getFromBranch() {
		return fromBranch;
	}

	public void setFromBranch(String fromBranch) {
		this.fromBranch = fromBranch;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

}
