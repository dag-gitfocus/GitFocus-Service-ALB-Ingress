package com.gitfocus.db.ui.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gitfocus.db.ui.model.PullRequestCount;

/**
 * @author Tech Mahindra
 *
 */
public interface IPullMasterService {
    /**
     * 
     * @param teamName
     * @param repoName
     * @param timeperiod
     * @param endDate
     * @return TeamMembersPullDetails
     */
    List<PullRequestCount> getCountOfPR(String teamName, String repoName, String timeperiod, String endDate)
            throws JsonProcessingException;

}
