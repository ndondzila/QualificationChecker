package com.qualificationchecker.Qualification.Checker.Models.Data;

import com.qualificationchecker.Qualification.Checker.Models.Weightlifter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface WeightlifterDAO extends CrudRepository<Weightlifter, Integer> { }