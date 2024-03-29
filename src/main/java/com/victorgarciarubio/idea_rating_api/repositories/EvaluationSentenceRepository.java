package com.victorgarciarubio.idea_rating_api.repositories;

import com.victorgarciarubio.idea_rating_api.models.EvaluationSentence;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EvaluationSentenceRepository extends CrudRepository<EvaluationSentence, Long> {

    Optional<EvaluationSentence> findById(Long evaluationSentenceId);
}
