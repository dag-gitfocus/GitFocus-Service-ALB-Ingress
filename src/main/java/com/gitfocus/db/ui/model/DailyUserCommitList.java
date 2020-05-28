package com.gitfocus.db.ui.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * @author Tech Mahindra 
 * A class for showing daily user commit details based on date
 */
public class DailyUserCommitList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	private Timestamp commitDate = null;
	private String[] fileNameArray;
	private String[] linesAddedArray;
	private String[] fileStatusArray;
	private String[] linesRemovedArray;
	private float x;
	
	/**
	 * 
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 
	 * @return commitDate
	 */
	public Timestamp getCommitDate() {
		return commitDate;
	}
	/**
	 * 
	 * @param commitDate
	 */
	public void setCommitDate(Timestamp commitDate) {
		this.commitDate = commitDate;
	}
	/**
	 * 
	 * @return fileNameArray
	 */
	public String[] getFileNameArray() {
		return fileNameArray;
	}
	/**
	 * 
	 * @param fileNameArray
	 */
	public void setFileNameArray(String[] fileNameArray) {
		this.fileNameArray = fileNameArray;
	}
	/**
	 * 
	 * @return linesAddedArray
	 */
	public String[] getLinesAddedArray() {
		return linesAddedArray;
	}
	/**
	 * 
	 * @param linesAddedArray
	 */
	public void setLinesAddedArray(String[] linesAddedArray) {
		this.linesAddedArray = linesAddedArray;
	}
	/**
	 * 
	 * @return fileStatusArray
	 */
	public String[] getFileStatusArray() {
		return fileStatusArray;
	}
	/**
	 * 
	 * @param fileStatusArray
	 */
	public void setFileStatusArray(String[] fileStatusArray) {
		this.fileStatusArray = fileStatusArray;
	}
	/**
	 * 
	 * @return linesRemovedArray
	 */
	public String[] getLinesRemovedArray() {
		return linesRemovedArray;
	}
	/**
	 * 
	 * @param linesRemovedArray
	 */
	public void setLinesRemovedArray(String[] linesRemovedArray) {
		this.linesRemovedArray = linesRemovedArray;
	}
	/**
	 * 
	 * @return x
	 */
	public float getX() {
		return x;
	}
	/**
	 * 
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commitDate == null) ? 0 : commitDate.hashCode());
		result = prime * result + Arrays.hashCode(fileNameArray);
		result = prime * result + Arrays.hashCode(fileStatusArray);
		result = prime * result + Arrays.hashCode(linesAddedArray);
		result = prime * result + Arrays.hashCode(linesRemovedArray);
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + Float.floatToIntBits(x);
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
		DailyUserCommitList other = (DailyUserCommitList) obj;
		if (commitDate == null) {
			if (other.commitDate != null)
				return false;
		} else if (!commitDate.equals(other.commitDate))
			return false;
		if (!Arrays.equals(fileNameArray, other.fileNameArray))
			return false;
		if (!Arrays.equals(fileStatusArray, other.fileStatusArray))
			return false;
		if (!Arrays.equals(linesAddedArray, other.linesAddedArray))
			return false;
		if (!Arrays.equals(linesRemovedArray, other.linesRemovedArray))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DailyUserCommitList [userId=" + userId + ", commitDate=" + commitDate + ", fileNameArray="
				+ Arrays.toString(fileNameArray) + ", linesAddedArray=" + Arrays.toString(linesAddedArray)
				+ ", fileStatusArray=" + Arrays.toString(fileStatusArray) + ", linesRemovedArray="
				+ Arrays.toString(linesRemovedArray) + ", x=" + x + "]";
	}

}
