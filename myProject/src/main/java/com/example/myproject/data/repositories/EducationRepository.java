package com.example.myproject.data.repositories;

import com.example.myproject.data.models.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education,String> {
    @Transactional
    Optional<Education> findById(String id);

    @Query("select r from Education r inner join r.user u where u.username in :username")
    List<Education> getAllByUsername(String username);
}
