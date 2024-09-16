package com.zekiyetekin.onlineBlogging.repository;

import com.zekiyetekin.onlineBlogging.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
