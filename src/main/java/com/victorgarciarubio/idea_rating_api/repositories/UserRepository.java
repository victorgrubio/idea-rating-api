package com.victorgarciarubio.idea_rating_api.repositories;

import com.victorgarciarubio.idea_rating_api.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    public User existsUserByUsername(String userId);
}
