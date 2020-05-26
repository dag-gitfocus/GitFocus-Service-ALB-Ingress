package com.gitfocus.db.ui.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 
 * @author Tech Mahindra 
 * A class for showing team members commit details and count based on PR
 * 
 * 
 */
public class TeamMembersCommitDetailBasedOnPR implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TeamMembersCommitDetailBasedOnPR() {
		super();
	}

	private String user = null;
	private String[] commitMessageArray = null;
	private String totalFilesAdded = null;
	private String totalFilesModified = null;
	private String linesAdded = null;
	private String linesRemoved = null;

	/**
	 * 
	 * @return user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * 
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * 
	 * @return commitMessageArray
	 */
	public String[] getCommitMessageArray() {
		return commitMessageArray;
	}

	/**
	 * 
	 * @param commitMessageArray
	 */
	public void setCommitMessageArray(String[] commitMessageArray) {
		this.commitMessageArray = commitMessageArray;
	}

	/**
	 * 
	 * @return totalFilesAdded
	 */
	public String getTotalFilesModified() {
		return totalFilesModified;
	}

	/**
	 * 
	 * @param totalFilesModified
	 */
	public void setTotalFilesModified(String totalFilesModified) {
		this.totalFilesModified = totalFilesModified;
	}

	/**
	 * 
	 * @return totalFilesAdded
	 */
	public String getTotalFilesAdded() {
		return totalFilesAdded;
	}

	/**
	 * 
	 * @param totalFilesAdded
	 */
	public void setTotalFilesAdded(String totalFilesAdded) {
		this.totalFilesAdded = totalFilesAdded;
	}

	/**
	 * 
	 * @return linesAdded
	 */
	public String getLinesAdded() {
		return linesAdded;
	}

	/**
	 * 
	 * @param linesAdded
	 */
	public void setLinesAdded(String linesAdded) {
		this.linesAdded = linesAdded;
	}

	/**
	 * 
	 * @return linesRemoved
	 */
	public String getLinesRemoved() {
		return linesRemoved;
	}

	/**
	 * 
	 * @param linesRemoved
	 */
	public void setLinesRemoved(String linesRemoved) {
		this.linesRemoved = linesRemoved;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(commitMessageArray);
		result = prime * result + ((linesAdded == null) ? 0 : linesAdded.hashCode());
		result = prime * result + ((linesRemoved == null) ? 0 : linesRemoved.hashCode());
		result = prime * result + ((totalFilesAdded == null) ? 0 : totalFilesAdded.hashCode());
		result = prime * result + ((totalFilesModified == null) ? 0 : totalFilesModified.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamMembersCommitDetailBasedOnPR other = (TeamMembersCommitDetailBasedOnPR) obj;
		if (!Arrays.equals(commitMessageArray, other.commitMessageArray))
			return false;
		if (linesAdded == null) {
			if (other.linesAdded != null)
				return false;
		} else if (!linesAdded.equals(other.linesAdded))
			return false;
		if (linesRemoved == null) {
			if (other.linesRemoved != null)
				return false;
		} else if (!linesRemoved.equals(other.linesRemoved))
			return false;
		if (totalFilesAdded == null) {
			if (other.totalFilesAdded != null)
				return false;
		} else if (!totalFilesAdded.equals(other.totalFilesAdded))
			return false;
		if (totalFilesModified == null) {
			if (other.totalFilesModified != null)
				return false;
		} else if (!totalFilesModified.equals(other.totalFilesModified))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TeamMembersCommitDetailBasedOnPR [user=" + user + ", commitMessageArray="
				+ Arrays.toString(commitMessageArray) + ", totalFilesAdded=" + totalFilesAdded + ", totalFilesModified="
				+ totalFilesModified + ", linesAdded=" + linesAdded + ", linesRemoved=" + linesRemoved + "]";
	}

}