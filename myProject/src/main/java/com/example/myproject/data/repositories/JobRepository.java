package com.example.myproject.data.repositories;

import com.example.myproject.data.models.Education;
import com.example.myproject.data.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job,String> {
    @Transactional
    Optional<Job> findById(String id);

    @Query("select r from Job r inner join r.user u where u.username in :username")
    List<Job> getAllByUsername(String username);
}
