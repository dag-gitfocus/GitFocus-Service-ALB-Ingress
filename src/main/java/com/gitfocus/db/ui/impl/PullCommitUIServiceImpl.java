package com.gitfocus.db.ui.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gitfocus.db.ui.model.TeamMembersCommitDetailBasedOnPR;
import com.gitfocus.db.ui.service.IPullCommitUIService;
import com.gitfocus.exception.ResourceNotFoundException;
import com.gitfocus.repository.CommitDetailsRepository;
import com.gitfocus.repository.PullCommitRepository;
import com.gitfocus.repository.TeamMembersRepository;
import com.gitfocus.repository.UnitReposRepository;
import com.gitfocus.util.GitFocusUtil;

/**
 * @author Tech Mahindra
 * Service class for PullCommit Service fetch the data from DB and show in GUI
 */
@Service
public class PullCommitUIServiceImpl implements IPullCommitUIService {

	private static Logger logger = LogManager.getLogger(PullCommitUIServiceImpl.class);

	public PullCommitUIServiceImpl() {
		super();
		logger.info("PullCommitUIServiceImpl init");
	}

	@Autowired
	private UnitReposRepository uReposRepository;
	@Autowired
	GitFocusUtil gitUtil;
	@Autowired
	CommitDetailsRepository commitRepository;
	@Autowired
	TeamMembersRepository teamMemRepos;
	@Autowired
	PullCommitRepository pullCommitRepository;

	@Override
	public List<TeamMembersCommitDetailBasedOnPR> getCommitDetailsBasedOnPR(int pullNo, String branchName, String repoName) throws ParseException {
		logger.info("commitDetailBasedOnPR " + pullNo, branchName, repoName);
		
		int totalLinesAdded = 0;
		int totalLinesRemoved = 0;
		int totalFilesAdded = 0;
		int totalFilesModified = 0;
		String userName = null;
		String[] linesAddedArr = null;
		String[] linesRemovedArr = null;
		String[] fileStatusArr = null;
		String[] messageArr = null;
		ArrayList<String> msgList = new ArrayList<String>();
		List<Object[]> commitdetails = new ArrayList<Object[]>();
		
		List<TeamMembersCommitDetailBasedOnPR> commitList = new ArrayList<TeamMembersCommitDetailBasedOnPR>();
		TeamMembersCommitDetailBasedOnPR model = new TeamMembersCommitDetailBasedOnPR();
		
		int repoId = uReposRepository.findRepoId(repoName);
		commitdetails = pullCommitRepository.getCommitDetailsBasedOnPR(pullNo, branchName, repoId);
		for (Object[] obj : commitdetails) {
			userName = (String) obj[0];
			msgList.add((String) obj[2]);
			String fileStatus = (String) obj[3];
			String linesAdded = (String) obj[4];
			String linedRemoved = (String) obj[5];
			fileStatusArr = fileStatus.split(",");
			linesAddedArr = linesAdded.split(",");
			linesRemovedArr = linedRemoved.split(",");

			for (String fileStatusObj : fileStatusArr) {
				if (fileStatusObj.equalsIgnoreCase("modified")) {
					totalFilesModified = totalFilesModified + 1;
				}
				if (fileStatusObj.equalsIgnoreCase("added")) {
					totalFilesAdded = totalFilesAdded + 1;
				}
			}
			for (String linesAddedObj : linesAddedArr) {
				totalLinesAdded = totalLinesAdded + Integer.parseInt(linesAddedObj);
			}
			for (String linesRemovedObj : linesRemovedArr) {
				totalLinesRemoved = totalLinesRemoved + Integer.parseInt(linesRemovedObj);
			}
		}
		model.setUser(userName);
		messageArr = msgList.toArray(new String[msgList.size()]);
		model.setCommitMessageArray(messageArr);
		model.setTotalFilesAdded(String.valueOf(totalFilesAdded));
		model.setTotalFilesModified(String.valueOf(totalFilesModified));
		model.setLinesAdded(String.valueOf(totalLinesAdded));
		model.setLinesRemoved(String.valueOf(totalLinesRemoved));

		commitList.add(model);

		if (commitList.isEmpty()) {
			logger.error("There is no Records for particular request on pullCommitdetailsBasedOnPR" + pullNo, branchName, repoName);
			
			throw new ResourceNotFoundException("There is no Records for particular request on CommitDetailsService",
					branchName, repoName);
		}

		logger.info("Data processed successfully for  pullCommitdetailsBasedOnPR" + pullNo, branchName, repoName);

		return commitList;
	}
}
