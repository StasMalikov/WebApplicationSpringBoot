package com.example.sweater.repos;

import com.example.sweater.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<Store, Long> {
    Store findByShortName(String shortName);
}
