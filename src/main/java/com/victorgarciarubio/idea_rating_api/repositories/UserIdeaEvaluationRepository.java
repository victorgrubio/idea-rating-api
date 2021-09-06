package com.victorgarciarubio.idea_rating_api.repositories;

import com.victorgarciarubio.idea_rating_api.models.EvaluationSentence;
import com.victorgarciarubio.idea_rating_api.models.UserIdeaEvaluation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserIdeaEvaluationRepository extends CrudRepository<UserIdeaEvaluation, Long> {

    Optional<UserIdeaEvaluation> findById(Long evaluationSentenceId);
}
