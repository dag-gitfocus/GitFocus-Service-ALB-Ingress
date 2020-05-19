package com.gitfocus.db.ui.impl;

import java.math.BigInteger;
import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gitfocus.db.ui.model.PullMasterCommitDetailOnDate;
import com.gitfocus.db.ui.model.PullRequestCount;
import com.gitfocus.db.ui.service.IPullMasterUIService;
import com.gitfocus.exception.ResourceNotFoundException;
import com.gitfocus.repository.PullMasterRepository;
import com.gitfocus.repository.TeamMembersRepository;
import com.gitfocus.repository.UnitReposRepository;
import com.gitfocus.util.GitFocusUtil;

/**
 * 
 * @author Tech Mahindra 
 * Service class for PullMaster Service fetch the data from DB and show in GUI
 * 
 */
@Service
public class PullMasterUIServiceImpl implements IPullMasterUIService {

	private static Logger logger = LogManager.getLogger(PullMasterUIServiceImpl.class);

	public PullMasterUIServiceImpl() {
		super();
		logger.info("PullMasterUIServiceImpl init");
	}

	@Autowired
	TeamMembersRepository teamMemRepos;
	@Autowired
	PullMasterRepository pMasterRepository;
	@Autowired
	private UnitReposRepository uReposRepository;
	@Autowired
	PullMasterRepository pullRepository;

	@Override
	public List<PullRequestCount> getCountOfPR(String teamName, String repoName, String timeperiod, String endDate)
			throws JsonProcessingException {
		// TODO Auto-generated method stub
		logger.info("getCountofPR " + teamName, repoName, timeperiod, endDate);
		SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy");
		List<Object> teamMembers = null;
		List<Object[]> memberPullList = new ArrayList<Object[]>();
		List<Object[]> memberPullListResults = new ArrayList<Object[]>();
		ArrayList<PullRequestCount> pullList = new ArrayList<PullRequestCount>();
		int pCount = 0;

		// get team_memebers based on team_name
		teamMembers = teamMemRepos.getTeamMembersByTeamName(teamName);

		if (timeperiod.equalsIgnoreCase("oneweek")) {
			for (Object userId : teamMembers) {
				memberPullList = pMasterRepository.getPullDetailsForMemberForOneWeek(repoName, userId.toString(), endDate);
				for (Object[] obj : memberPullList) {
					pCount = ((BigInteger) obj[3]).intValue();
					// If PRcount is 0 then userId also 0
					// set userId even if PRcount is 0
					if (pCount == 0) {
						obj[1] = userId.toString();
					}
				}
				memberPullListResults.addAll(memberPullList);
			}
		}
		if (timeperiod.equalsIgnoreCase("twoweek")) {
			for (Object userId : teamMembers) {
				memberPullList = pMasterRepository.getPullDetailsForMemberForTwoWeeks(repoName, userId.toString(), endDate);
				for (Object[] obj : memberPullList) {
					pCount = ((BigInteger) obj[3]).intValue();
					// If PRcount is 0 then userId also 0
					// set userId even if PRcount is 0
					if (pCount == 0) {
						obj[1] = userId.toString();
					}
				}
				memberPullListResults.addAll(memberPullList);
			}
		}
		for (Object[] obj : memberPullListResults) {
			PullRequestCount model = new PullRequestCount();
			String user = (String) obj[1];
			String createdDate = sd.format(obj[2]);
			pCount = ((BigInteger) obj[3]).intValue();
			String prCount = String.valueOf(pCount);

			model.setUser(user);
			model.setPrCreatedDate(createdDate);
			model.setPrCount(prCount);

			pullList.add(model);

			if (pullList.size() == 0) {
				logger.error("There is no Records for particular request on getCountofPR " + teamName, repoName, timeperiod, endDate);

				throw new ResourceNotFoundException("There is no Records for particular request on PullDetailsService",	teamName, repoName);
			}
		}

		logger.info("Data processed successfully for getCountofPR()  " + teamName, repoName, timeperiod, endDate);

		return pullList;
	}

	@Override
	public List<PullMasterCommitDetailOnDate> pullDetailOnDateForTeamMemeber(String userName, String repoName, String commitDate) throws ParseException {
		// TODO Auto-generated method stub
		logger.info("pullDetailOnDateForTeamMemeber " + userName, repoName, commitDate);

		List<Object[]> pullMasterList = new ArrayList<Object[]>();
		ArrayList<PullMasterCommitDetailOnDate> pullList = new ArrayList<PullMasterCommitDetailOnDate>();
		PullMasterCommitDetailOnDate model = new PullMasterCommitDetailOnDate();
		boolean merged = false;
		String mergedStatus = new String();
		String pullStatus = new String();
		String fromBranch = new String();
		String createdTime = new String();
		Date cDate = null;

		// get startDate and endDate
		Date[] inputDates = GitFocusUtil.getStartAndEndDate(commitDate);
		int repoId = uReposRepository.findRepoId(repoName);

		// get pullMasterDetails based on userName, repoId, startDate and endDate
		pullMasterList = pullRepository.getPullDetailOnDateForMemebers(userName, repoId, inputDates[0], inputDates[1]);

		for (Object[] obj : pullMasterList) {
			merged = (boolean) obj[0];
			mergedStatus = mergedStatus.concat(",") + merged;
			pullStatus = pullStatus.concat(",") + (String) obj[1];
			fromBranch = fromBranch.concat(",") + (String) obj[2];
			cDate = (Date) obj[3];
			createdTime = createdTime.concat(",") + GitFocusUtil.convertDateToString(cDate);
		}
		model.setUser(userName);
		model.setRepoName(repoName);
		model.setMerged(mergedStatus.substring(1));
		model.setPullStatus(pullStatus.substring(1));
		model.setFromBranch(fromBranch.substring(1));
		model.setCreatedTime(createdTime.substring(1));

		pullList.add(model);

		if (pullList.size() == 0) {
			logger.error("There is no Records for particular request on pullDetailOnDateForTeamMemeber " + userName, repoName, commitDate);
			throw new ResourceNotFoundException("There is no Records for particular request on pullDetailOnDateForTeamMemeber", userName, repoName);
		}

		logger.info("Data processed successfully for pullDetailOnDateForTeamMemeber()  " + userName, repoName, commitDate);
		return pullList;
	}
}