package com.gitfocus.db.ui.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gitfocus.db.ui.model.PullRequestCount;
import com.gitfocus.db.ui.service.IPullMasterService;

/**
 * @author Tech Mahindra
 * Controller class for pull_commit service from database to GUI
 */

@RestController
public class PullDetailsController {

    private static final Logger logger = LoggerFactory.getLogger(PullDetailsController.class);

    public PullDetailsController() {
        super();
        logger.info("PullDetailsController init");
    }

    @Autowired
    IPullMasterService pullMasterService;

    /**
     * 
     * @param teamName
     * @param repoName
     * @param timeperiod
     * @return dateBasedPullDetailsForTeamMembers
     */
    @GetMapping("/gitfocus/getpullrequestcount/{teamName}/{repoName}/{timeperiod}/{endDate}")
    public List<PullRequestCount> getCountofPR(@PathVariable("teamName") String teamName,
            @PathVariable("repoName") String repoName, @PathVariable("timeperiod") String timeperiod,
            @PathVariable("endDate") String endDate) throws JsonProcessingException {

        logger.info("getCountofPR", teamName, repoName, timeperiod, endDate);
        List<PullRequestCount> pullDetailsJson = null;
        pullDetailsJson = pullMasterService.getCountOfPR(teamName, repoName, timeperiod, endDate);
        logger.info("getRepoPullCountWeek Records", pullDetailsJson);
        return pullDetailsJson;
    }
}
