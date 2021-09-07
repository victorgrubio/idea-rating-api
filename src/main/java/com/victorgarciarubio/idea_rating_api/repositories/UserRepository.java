package com.victorgarciarubio.idea_rating_api.repositories;

import com.victorgarciarubio.idea_rating_api.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {


    boolean existsUserByUsername(String username);
}
