package com.gitfocus.db.ui.model;

import java.io.Serializable;

/**
 * @author Tech Mahindra 
 * A class for showing team members commit details and count based on date
 */
public class TeamMembersCommitDetailOnDate implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String user = null;
    private String commitDate = null;
    private String[] commitMessageArray = null;
    private String totalFilesAdded = null;
    private String totalFilesModified = null;
    private String linesAdded = null;
    private String linesRemoved = null;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(String commitDate) {
        this.commitDate = commitDate;
    }

    public String[] getCommitMessageArray() {
        return commitMessageArray;
    }

    public void setCommitMessageArray(String[] commitMessageArray) {
        this.commitMessageArray = commitMessageArray;
    }

    public String getTotalFilesModified() {
        return totalFilesModified;
    }

    public void setTotalFilesModified(String totalFilesModified) {
        this.totalFilesModified = totalFilesModified;
    }

    public String getTotalFilesAdded() {
        return totalFilesAdded;
    }

    public void setTotalFilesAdded(String totalFilesAdded) {
        this.totalFilesAdded = totalFilesAdded;
    }

    public String getLinesAdded() {
        return linesAdded;
    }

    public void setLinesAdded(String linesAdded) {
        this.linesAdded = linesAdded;
    }

    public String getLinesRemoved() {
        return linesRemoved;
    }

    public void setLinesRemoved(String linesRemoved) {
        this.linesRemoved = linesRemoved;
    }

}