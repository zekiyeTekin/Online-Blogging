package com.zekiyetekin.onlineblogging.repository;

import com.zekiyetekin.onlineblogging.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
