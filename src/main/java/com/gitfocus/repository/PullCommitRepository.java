package com.gitfocus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gitfocus.git.db.model.PullCommit;

/**
 * @author Tech Mahindra
 *
 */
@Repository
public interface PullCommitRepository extends JpaRepository<PullCommit, Object> {

}
