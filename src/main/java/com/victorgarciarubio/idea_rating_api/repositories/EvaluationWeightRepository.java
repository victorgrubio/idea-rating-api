package com.victorgarciarubio.idea_rating_api.repositories;

import com.victorgarciarubio.idea_rating_api.models.EvaluationWeight;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EvaluationWeightRepository extends CrudRepository<EvaluationWeight, Long> {

    List<EvaluationWeight> findAll();
}
