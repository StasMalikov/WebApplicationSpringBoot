package com.example.sweater.repos;

import com.example.sweater.domain.MyUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepo extends CrudRepository<MyUser, Long> {

    List<MyUser> findByName(String name);
}
