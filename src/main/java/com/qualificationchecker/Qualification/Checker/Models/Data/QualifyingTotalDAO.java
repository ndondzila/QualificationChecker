package com.qualificationchecker.Qualification.Checker.Models.Data;

import com.qualificationchecker.Qualification.Checker.Models.QualifyingTotal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface QualifyingTotalDAO extends CrudRepository<QualifyingTotal, Integer> { }

