package com.gitfocus.git.db.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gitfocus.constants.GitFocusConstants;
import com.gitfocus.git.db.model.CommitDetails;
import com.gitfocus.git.db.model.CommitDetailsCompositeId;
import com.gitfocus.git.db.model.Units;
import com.gitfocus.git.db.service.ICommitDetailGitService;
import com.gitfocus.repository.BranchDetailsRepository;
import com.gitfocus.repository.CommitDetailsRepository;
import com.gitfocus.repository.TeamMembersRepository;
import com.gitfocus.repository.UnitReposRepository;
import com.gitfocus.repository.UnitsRepository;
import com.gitfocus.util.GitFocusUtil;

/**
 * @author Tech Mahindra 
 * Service class for CommitDetails and store values in commit_details table in DB
 * 
 * NOTE : By default GitAPIJson gives max 30 records only for each RestAPI call but for some API have more than 30 records, hence we to
 * append page number and totalNoOfRecords/perPage for each URL's to fetch rest of the records
 * 
 */
@Service
public class CommitDetailGitServiceImpl implements ICommitDetailGitService {

    private final static Logger logger = LoggerFactory.getLogger(CommitDetailGitServiceImpl.class);

    public CommitDetailGitServiceImpl() {
        super();
        logger.info("CommitDetailServiceImpl init");
    }

    @Autowired
    private GitFocusConstants gitConstant;
    @Autowired
    private UnitReposRepository uReposRepository;
    @Autowired
    private UnitsRepository uRepository;
    @Autowired
    private CommitDetailsRepository cDetailsRepository;
    @Autowired
    private BranchDetailsRepository bDetailsRepository;
    @Autowired
    GitFocusUtil gitUtil;
    @Autowired
    CommitDetailsRepository commitRepository;
    @Autowired
    TeamMembersRepository teamMemRepos;

    List<Units> unitsRecords = null;
    List<Units> units = null;
    String jsonResult = null;
    String unitOwner = null;
    int unitId = 0;
    int repoId = 0;
    String commitsResult = null;
    String shaId = null;
    String commitsUri = null;
    JSONArray jsonResponse = null;
    JSONObject commitDetailObj = null;
    JSONObject commitObj1 = null;
    JSONObject commitObj2 = null;
    String userId = null;
    String commitDetailURI = null;
    String commitDetailShaURI = null;
    String commitDetailShaResult = null;
    JSONObject commitDetailShaObj = null;
    JSONArray commitShaArr = null;
    JSONObject jsonObj = null;
    JSONObject jsonShaObj = null;
    Date cDate = null;
    String commitDate = null;
    String messgae = null;
    List<String> reposName = null;
    List<String> branches = null;
    CommitDetails cDetails = new CommitDetails();
    CommitDetailsCompositeId commitCompositeId = new CommitDetailsCompositeId();

    /**
     * 
     * @return boolean
     * @throws ParseException
     */
    @Override
    public boolean save() {
        logger.info("CommitDetailServiceImpl save()");
        boolean result = false;
        units = (List<Units>) uRepository.findAll();
        if (units.isEmpty()) {
            return result;
        }
        units.forEach(response -> {
            unitId = response.getUnitId();
            unitOwner = response.getUnitOwner();
            reposName = uReposRepository.findReposName(unitId);

            reposName.forEach(repoName -> {
                repoId = uReposRepository.findRepoId(repoName);
                // get branches for repository(reposName)
                branches = bDetailsRepository.getBranchList(repoId);

                branches.forEach(branchName -> {
                    for (int page = 0; page <= gitConstant.MAX_PAGE; page++) {
                        commitDetailURI = gitConstant.BASE_URI + unitOwner + "/" + repoName + "/commits?" + "sha="
                                + branchName + "&page=" + page + "&" + "per_page=" + gitConstant.PER_PAGE_TOTAL_RECORDS + "&";
                        commitsResult = gitUtil.getGitAPIJsonResponse(commitDetailURI);
                        jsonResponse = new JSONArray(commitsResult);

                        for (int i = 0; i < jsonResponse.length(); i++) {
                            commitDetailObj = jsonResponse.getJSONObject(i);

                            commitObj1 = commitDetailObj.getJSONObject("commit");
                            commitObj2 = commitObj1.getJSONObject("author");

                            shaId = commitDetailObj.getString("sha");
                            userId = commitObj2.getString("name");
                            commitDate = commitObj2.getString("date");
                            cDate = GitFocusUtil.stringToDate(commitDate);
                            messgae = commitObj1.getString("message");

                            // commit_detail based on sha_id
                            if (shaId != null) {

                                commitDetailShaURI = gitConstant.BASE_URI + unitOwner + "/" + repoName + "/commits/"
                                        + shaId + "?";

                                commitDetailShaResult = gitUtil.getGitAPIJsonResponse(commitDetailShaURI);
                                commitDetailShaObj = new JSONObject(commitDetailShaResult);
                                commitShaArr = commitDetailShaObj.getJSONArray("files");

                                String fileName = null;
                                String fileStatus = null;
                                String linesAdded = null;
                                String linesRemoved = null;

                                for (int j = 0; j < commitShaArr.length(); j++) {
                                    jsonShaObj = commitShaArr.getJSONObject(j);
                                    if (fileName == null) {
                                        fileName = jsonShaObj.getString("filename").concat(",");
                                    } else {
                                        fileName = fileName + jsonShaObj.getString("filename").concat(",");
                                    }
                                    if (fileStatus == null) {
                                        fileStatus = jsonShaObj.getString("status").concat(",");
                                    } else {
                                        fileStatus = fileStatus + jsonShaObj.getString("status").concat(",");
                                    }
                                    if (linesAdded == null) {
                                        linesAdded = String.valueOf(jsonShaObj.getInt("additions")).concat(",");
                                    } else {
                                        linesAdded = linesAdded
                                                + String.valueOf(jsonShaObj.getInt("additions")).concat(",");
                                    }
                                    if (linesRemoved == null) {
                                        linesRemoved = String.valueOf(jsonShaObj.getInt("deletions")).concat(",");
                                    } else {
                                        linesRemoved = linesRemoved
                                                + String.valueOf(jsonShaObj.getInt("deletions")).concat(",");
                                    }

                                    commitCompositeId.setUnitId(unitId);
                                    commitCompositeId.setShaId(shaId);
                                    commitCompositeId.setRepoId(repoId);
                                    commitCompositeId.setBranchName(branchName);

                                    cDetails.setcCompositeId(commitCompositeId);

                                    cDetails.setCommitDate(cDate);
                                    cDetails.setUserId(userId);
                                    cDetails.setMessage(messgae);
                                    cDetails.setFileName(fileName);
                                    cDetails.setFileStatus(fileStatus);
                                    cDetails.setLinesAdded(linesAdded);
                                    cDetails.setLinesRemoved(linesRemoved);

                                    cDetailsRepository.save(cDetails);

                                    logger.info("Records saved in commit_details table in DB");
                                }
                            }
                        }
                    }
                });
            });
        });
        return true;
    }
}
