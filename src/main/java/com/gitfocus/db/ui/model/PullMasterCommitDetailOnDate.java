package com.gitfocus.db.ui.model;

import java.io.Serializable;

/**
 * 
 * @author Tech Mahindra 
 * A class for showing pull master commit details and count based on date
 * 
 */
public class PullMasterCommitDetailOnDate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PullMasterCommitDetailOnDate() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String user = null;
	private String repoName = null;
	private String commitDate = null;
	private String[] pullNo = null;
	private Boolean[] merged = null;
	private Boolean[] notMerged = null;
	private String[] branchName = null;
	private String[] createdTime = null;

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

	public String getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(String commitDate) {
		this.commitDate = commitDate;
	}

	public String[] getPullNo() {
		return pullNo;
	}

	public void setPullNo(String[] pullNo) {
		this.pullNo = pullNo;
	}

	public Boolean[] getMerged() {
		return merged;
	}

	public void setMerged(Boolean[] merged) {
		this.merged = merged;
	}

	public Boolean[] getNotMerged() {
		return notMerged;
	}

	public void setNotMerged(Boolean[] notMerged) {
		this.notMerged = notMerged;
	}

	public String[] getBranchName() {
		return branchName;
	}

	public void setBranchName(String[] branchName) {
		this.branchName = branchName;
	}

	public String[] getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String[] createdTime) {
		this.createdTime = createdTime;
	}

}
