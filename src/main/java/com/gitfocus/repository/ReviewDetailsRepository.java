package com.gitfocus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gitfocus.git.db.model.ReviewDetails;

/**
 * @author Tech Mahindra 
 * 
 * Repository class for review_details table in DB
 *
 */

@Repository
public interface ReviewDetailsRepository extends JpaRepository<ReviewDetails, Object> {

	/**
	 * 
	 * @param member
	 * @param repoName
	 * @param endDate
	 * @return getCommitDetailsForMemberForOneWeek Results
	 */
	@Query(value = "SELECT ur.repo_name ,rd.reviewed_by,d,count(rd.reviewed_at) from gitfocus.review_details\r\n"
			+ "rd join gitfocus.branch_details bd ON (rd.repo_id=bd.repo_id)\r\n"
			+ "join gitfocus.unit_repos ur on (ur.repo_id=bd.repo_id) RIGHT JOIN generate_series( \r\n"
			+ "date_trunc('day', (cast(?3 as timestamp) - interval '6 days' )), \r\n"
			+ "date_trunc('day', cast(?3 as timestamp)),'1 day' \r\n"
			+ ") AS gs(d) ON d = date_trunc('day',rd.reviewed_at) and \r\n"
			+ "ur.repo_name=?1 and rd.reviewed_by=?2 group by ur.repo_name,rd.reviewed_by, d order by d", nativeQuery = true)
	List<Object[]> getCommitDetailsForMemberForOneWeek(String repoName, String userId, String endDate);

	/**
	 * 
	 * @param member
	 * @param repoName
	 * @param endDate
	 * @return getCommitDetailsForMemberForTwoWeek Results
	 */
	@Query(value = "SELECT ur.repo_name ,rd.reviewed_by,d,count(rd.reviewed_at) from gitfocus.review_details\\r\\n\"\r\n" + 
			"			+ \"rd join gitfocus.branch_details bd ON (rd.repo_id=bd.repo_id)\\r\\n\"\r\n" + 
			"			+ \"join gitfocus.unit_repos ur on (ur.repo_id=bd.repo_id) RIGHT JOIN generate_series( \\r\\n\"\r\n" + 
			"			+ \"date_trunc('day', (cast(?3 as timestamp) - interval '13 days' )), \\r\\n\"\r\n" + 
			"			+ \"date_trunc('day', cast(?3 as timestamp)),'1 day' \\r\\n\"\r\n" + 
			"			+ \") AS gs(d) ON d = date_trunc('day',rd.reviewed_at) and \\r\\n\"\r\n" + 
			"			+ \"ur.repo_name=?1 and rd.reviewed_by=?2 group by ur.repo_name,rd.reviewed_by, d order by d", nativeQuery = true)
	List<Object[]> getCommitDetailsForMemberForTwoWeek(String repoName, String userId, String endDate);

}
