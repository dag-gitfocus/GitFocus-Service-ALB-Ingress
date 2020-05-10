package com.gitfocus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gitfocus.git.db.model.ReviewDetails;

public interface ReviewDetailsRepository extends JpaRepository<ReviewDetails, Object> {

}
