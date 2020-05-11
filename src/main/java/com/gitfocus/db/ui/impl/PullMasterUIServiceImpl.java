package com.gitfocus.db.ui.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gitfocus.db.ui.model.PullRequestCount;
import com.gitfocus.db.ui.service.IPullMasterUIService;
import com.gitfocus.exception.ResourceNotFoundException;
import com.gitfocus.repository.PullMasterRepository;
import com.gitfocus.repository.TeamMembersRepository;

/**
 * @author Tech Mahindra
 * Service class for PullMaster Service fetch the data from DB and show in GUI
 */
@Service
public class PullMasterUIServiceImpl implements IPullMasterUIService {

    @Autowired
    TeamMembersRepository teamMemRepos;
    @Autowired
    PullMasterRepository pMasterRepository;

    private final static Logger logger = LoggerFactory.getLogger(PullMasterUIServiceImpl.class);

    public PullMasterUIServiceImpl() {
        super();
        logger.info("BranchDetailServiceImpl init");
    }

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
                memberPullList = pMasterRepository.getPullDetailsForMemberForOneWeek(repoName, userId.toString(),
                        endDate);
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
                memberPullList = pMasterRepository.getPullDetailsForMemberForTwoWeeks(repoName, userId.toString(),
                        endDate);
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

            if (pullList.isEmpty()) {
                logger.error("There is no Records for particular request on getCountofPR " + teamName, repoName,
                        timeperiod, endDate);
                throw new ResourceNotFoundException("There is no Records for particular request on PullDetailsService",
                        teamName, repoName);
            }
        }

        logger.info("Data processed successfully for getCountofPR()  " + teamName, repoName, timeperiod, endDate);

        return pullList;
    }

}
