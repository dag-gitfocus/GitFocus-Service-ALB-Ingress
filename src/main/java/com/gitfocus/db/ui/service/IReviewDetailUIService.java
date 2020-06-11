package com.gitfocus.db.ui.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gitfocus.db.ui.model.ReviewDetailsForTeamMembers;

/**
 * @author Tech Mahindra
 *
 */

public interface IReviewDetailUIService {

	/**
	 * 
	 * @param teamName
	 * @param repoName
	 * @param timeperiod
	 * @param endDate
	 * @return TeamMembersCommitDetails
	 */
	public List<ReviewDetailsForTeamMembers> dateBasedReviewDetailsForTeamMembers(String teamName, String repoName,
			String timeperiod, String endDate) throws JsonProcessingException;

}
