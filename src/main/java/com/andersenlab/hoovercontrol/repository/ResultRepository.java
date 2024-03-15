package com.andersenlab.hoovercontrol.repository;

import com.andersenlab.hoovercontrol.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}
