package com.gitfocus.db.ui.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gitfocus.db.ui.model.ReviewDetailsForTeamMembers;
import com.gitfocus.db.ui.service.IReviewDetailUIService;
import com.gitfocus.exception.ResourceNotFoundException;
import com.gitfocus.repository.ReviewDetailsRepository;
import com.gitfocus.repository.TeamMembersRepository;
import com.gitfocus.util.GitFocusUtil;

/**
 * 
 * @author Tech Mahindra 
 * Service class for ReviewDetails to fetch the data from DB and show in GUI
 * 
 */
@Service
public class ReviewDetailsUIServiceImpl implements IReviewDetailUIService {

	private static final Logger logger = LogManager.getLogger(ReviewDetailsUIServiceImpl.class.getSimpleName());

	public ReviewDetailsUIServiceImpl() {
		super();
		logger.info("ReviewDetailsUIServiceImpl init");
	}

	@Autowired
	GitFocusUtil gitUtil;
	@Autowired
	ReviewDetailsRepository reviewDetailsRepo;
	@Autowired
	TeamMembersRepository teamMemRepos;

	@Override
	public List<ReviewDetailsForTeamMembers> dateBasedReviewDetailsForTeamMembers(String teamName, String repoName,
			String timeperiod, String endDate) throws JsonProcessingException {
		// TODO Auto-generated method stub
		logger.info("dateBasedCommitDetailsForTeamMembers " + teamName, repoName, timeperiod, endDate);
		SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy");
		List<Object> teamMembers = null;
		List<Object[]> memberReviewList = new ArrayList<Object[]>();
		List<Object[]> memberReviewListResults = new ArrayList<Object[]>();
		ArrayList<ReviewDetailsForTeamMembers> commitList = new ArrayList<ReviewDetailsForTeamMembers>();
		int cCount = 0;

		// get team_memebers based on team_name
		teamMembers = teamMemRepos.getTeamMembersByTeamName(teamName);
		// one week time period of data
		if (timeperiod.equalsIgnoreCase("oneweek")) {
			for (Object userId : teamMembers) {
				memberReviewList = reviewDetailsRepo.getCommitDetailsForMemberForOneWeek(repoName, userId.toString(),
						endDate);
				for (Object[] obj : memberReviewList) {
					cCount = ((BigInteger) obj[3]).intValue();
					// If commitcount is 0 then userId also 0
					// set userId even if commitcount is 0
					if (cCount == 0) {
						obj[1] = userId.toString();
					}
				}
				memberReviewListResults.addAll(memberReviewList);
			}
		}
		// two week time period of data
		if (timeperiod.equalsIgnoreCase("twoweek")) {
			for (Object userId : teamMembers) {
				memberReviewList = reviewDetailsRepo.getCommitDetailsForMemberForTwoWeek(repoName, userId.toString(),
						endDate);
				for (Object[] obj : memberReviewList) {
					cCount = ((BigInteger) obj[3]).intValue();
					// If commitcount is 0 then userId also 0
					// set userId even if commitcount is 0
					if (cCount == 0) {
						obj[1] = userId.toString();
					}
				}
				memberReviewListResults.addAll(memberReviewList);
			}
		}
		for (Object[] obj : memberReviewListResults) {
			ReviewDetailsForTeamMembers model = new ReviewDetailsForTeamMembers();
			String user = (String) obj[1];
			String commitDate = sd.format(obj[2]);
			cCount = ((BigInteger) obj[3]).intValue();
			String commitCount = String.valueOf(cCount);

			model.setUser(user);
			model.setCommitDate(commitDate);
			model.setCommitCount(commitCount);

			commitList.add(model);

			if (commitList.size() == 0) {
				logger.error("There is no Records for particular request on dateBasedCommitDetailsForTeamMembers "
						+ teamName, repoName, timeperiod, endDate);
				throw new ResourceNotFoundException(
						"There is no Records for particular request on CommitDetailsService", teamName, repoName);
			}
		}

		logger.info("Data processed successfully for dateBasedCommitDetailsForTeamMembers()  " + teamName, repoName,
				timeperiod, endDate);

		return commitList;
	}
}
