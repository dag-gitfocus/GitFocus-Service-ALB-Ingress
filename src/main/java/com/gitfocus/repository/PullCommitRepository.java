package com.gitfocus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gitfocus.git.db.model.PullCommit;

/**
 * @author Tech Mahindra
 *
 */
@Repository
public interface PullCommitRepository extends JpaRepository<PullCommit, Object> {

	/**
	 * 
	 * @param pullNo
	 * @param branchName
	 * @param repoId
	 * @return
	 */
	@Query("select cm.userId,cm.commitDate,cm.message,cm.fileStatus,cm.linesAdded,cm.linesRemoved,cm.fileName from CommitDetails cm\r\n"
			+ "JOIN PullCommit pc\r\n" + "ON cm.cCompositeId.shaId = pc.pCompositeId.commitId\r\n"
			+ "AND cm.cCompositeId.branchName = pc.pCompositeId.branchName\r\n"
			+ "AND cm.cCompositeId.repoId = pc.pCompositeId.repoId\r\n"
			+ "where pc.pCompositeId.pullNumber= :pullNo\r\n" + "and pc.pCompositeId.branchName= :branchName\r\n"
			+ "and pc.pCompositeId.repoId = :repoId")
	List<Object[]> getCommitDetailsBasedOnPR(int pullNo, String branchName, int repoId);

}
