package com.example.sweater.repos;

import com.example.sweater.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
