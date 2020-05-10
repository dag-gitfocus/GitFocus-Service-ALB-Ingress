package com.gitfocus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gitfocus.git.db.model.PullMaster;

/**
 * @author Tech Mahindra
 *
 */
@Repository
public interface PullMasterRepository extends JpaRepository<PullMaster, Object> {
    /**
     * 
     * @param repoId
     * @return pullNo
     */
    @Query("select p.pCompositeId.pullNumber from PullMaster p where p.pCompositeId.repoId=:repoId")
    List<String> findPullNo(int repoId);

    /**
     * 
     * @param member
     * @param repoName
     * @param endDate
     * @return getPullDetailsForMemberForTwoWeeks Results
     */
    @Query(value = "    SELECT  ur.repo_name,pm.user_id,d,count(pm.created_time)  \r\n"
            + "			From gitschema.pull_master pm    			join gitschema.branch_details bd  \r\n"
            + "			ON (pm.repo_id=bd.repo_id and pm.to_branch=bd.branch_name)  \r\n"
            + "			join gitschema.unit_repos ur on (ur.repo_id=bd.repo_id) \r\n" + "			RIGHT JOIN generate_series( \r\n"
            + "			date_trunc('day', (cast(?3 as timestamp) - interval '13 days' )), \r\n"
            + "			date_trunc('day', cast(?3 as timestamp)),  			'1 day' \r\n"
            + "			) AS gs(d) ON d = date_trunc('day',pm.created_time)  			and ur.repo_name=?1   \r\n"
            + "			and pm.user_id=?2   group by ur.repo_name ,pm.user_id,d \r\n" + "			order by d", nativeQuery = true)
    List<Object[]> getPullDetailsForMemberForTwoWeeks(String repoName, String userId, String endDate);

    /**
     * 
     * @param member
     * @param repoName
     * @param endDate
     * @return getPullDetailsForMemberForOneWeek Results
     */
    @Query(value = "SELECT  ur.repo_name,pm.user_id,d,count(pm.created_time)  \r\n"
            + "    					From gitschema.pull_master pm    			join gitschema.branch_details bd  \r\n"
            + "    					ON (pm.repo_id=bd.repo_id and pm.to_branch=bd.branch_name)  \r\n"
            + "    					join gitschema.unit_repos ur on (ur.repo_id=bd.repo_id) \r\n"
            + "    					RIGHT JOIN generate_series( \r\n"
            + "    					date_trunc('day', (cast(?3 as timestamp) - interval '6 days' )), \r\n"
            + "    					date_trunc('day', cast(?3 as timestamp)),  			'1 day' \r\n"
            + "    					) AS gs(d) ON d = date_trunc('day',pm.created_time)  			and ur.repo_name=?1   \r\n"
            + "    					and pm.user_id=?2   group by ur.repo_name ,pm.user_id,d \r\n" + "    					order by d", nativeQuery = true)
    List<Object[]> getPullDetailsForMemberForOneWeek(String repoName, String userId, String endDate);

}
