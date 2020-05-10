package com.gitfocus.git.db.controller;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gitfocus.git.db.service.IBranchDetailGitService;
import com.gitfocus.git.db.service.ICommitDetailGitService;
import com.gitfocus.git.db.service.IPullCommitGitService;
import com.gitfocus.git.db.service.IPullMasterGitService;

/**
 * @author Tech Mahindra 
 * Controller class for GitFocus-Service to store data from Git to Database
 */
@RestController
public class GitFocusGitController {

    private static final Logger logger = LoggerFactory.getLogger(GitFocusGitController.class);

    public GitFocusGitController() {
        super();
        logger.info("GitFocusController init");
    }

    @Autowired
    private ICommitDetailGitService commitDetailService;
    @Autowired
    private IBranchDetailGitService branchDetailService;
    @Autowired
    private IPullMasterGitService pullMasterService;
    @Autowired
    private IPullCommitGitService pullCommitService;

    boolean serviceResult = false;
    String serviceMessage = null;

    @GetMapping("/gitfocus/insertcommitdetail")
    public String getCommitDetailInfo() throws ParseException {
        logger.info("CommitDetail Service");
        serviceResult = commitDetailService.save();
        if (serviceResult = true) {
            serviceMessage = "Records inserted successfully in CommitDetail Service";
            logger.info("Records inserted successfully in CommitDetail Service");
        } else {
            serviceMessage = "Records not inserted successfully in CommitDetail Service";
            logger.info("Records inserted successfully in CommitDetail Service");
        }
        return serviceMessage;
    }

    @GetMapping("/gitfocus/insertbranchdetail")
    public String getBranchDetailInfo() throws ParseException {
        logger.info("BranchDetail Service");
        serviceResult = branchDetailService.save();
        if (serviceResult = true) {
            serviceMessage = "Records inserted successfully in BranchDetail Service";
            logger.info("Records inserted successfully in BranchDetail Service");
        } else {
            serviceMessage = "Records not inserted successfully in BranchDetail Service";
            logger.info("Records not inserted successfully in BranchDetail Service");

        }
        return serviceMessage;
    }

    @GetMapping("/gitfocus/insertpullmasterdetail")
    public String getPullMasterDetailInfo() throws ParseException {
        logger.info("PullMasterDetail Service");
        serviceResult = pullMasterService.save();
        if (serviceResult = true) {
            serviceMessage = "Records inserted successfully in PullMasterDetail Service";
            logger.info("Records inserted successfully in PullMasterDetail Service");
        } else {
            serviceMessage = "Records not inserted successfully in BranchDetail Service";
            logger.info("Records not inserted successfully in PullMasterDetail Service");

        }
        return serviceMessage;
    }

    @GetMapping("/gitfocus/insertpullcommitdetail")
    public String getPullCommitDetailInfo() throws ParseException {
        logger.info("PullCommitDetail Service");
        serviceResult = pullCommitService.save();
        if (serviceResult = true) {
            serviceMessage = "Records inserted successfully in PullCommitDetail Service";
            logger.info("Records inserted successfully in PullCommitDetail Service");
        } else {
            serviceMessage = "Records not inserted successfully in PullCommitDetail Service";
            logger.info("Records not inserted successfully in PullCommitDetail Service");

        }
        return serviceMessage;
    }
}
