package com.victorgarciarubio.idea_rating_api.repositories;

import com.victorgarciarubio.idea_rating_api.models.Idea;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IdeaRepository extends CrudRepository<Idea, Long> {

    @Override
    public List<Idea> findAll();

    public List<Idea> findIdeasByUser_Username(String username);

    public Optional<Idea> findById(Long ideaId);

    public void deleteIdeaByIdAndUser_Username(Long ideaId, String username);



}
