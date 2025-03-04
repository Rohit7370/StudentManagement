package com.student.repository;



import com.student.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    // Query to calculate total and average
    @Query("SELECT SUM(r.hindi + r.english + r.maths), AVG(r.hindi + r.english + r.maths) FROM Result r")
    Object[] findTotalAndAverage();
}

