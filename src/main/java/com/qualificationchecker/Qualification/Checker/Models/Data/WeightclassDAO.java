package com.qualificationchecker.Qualification.Checker.Models.Data;

import com.qualificationchecker.Qualification.Checker.Models.Weightclass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface WeightclassDAO extends CrudRepository<Weightclass, Integer> { }