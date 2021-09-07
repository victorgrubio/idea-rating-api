package com.victorgarciarubio.idea_rating_api.repositories;

import com.victorgarciarubio.idea_rating_api.models.Idea;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IdeaRepository extends CrudRepository<Idea, Long> {

    @Override
    List<Idea> findAll();

    List<Idea> findIdeasByUserUsername(String username);

    Optional<Idea> findById(Long ideaId);

    @Modifying
    @Transactional(rollbackFor = {SQLException.class})
    @Query("delete from Idea i where i.id = :ideaId and i.user.username = :username")
    void deleteIdeaByIdAndUserUsername(Long ideaId, String username);
}
