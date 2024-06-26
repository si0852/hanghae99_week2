package com.hhplus.lecture.infra;

import com.hhplus.lecture.business.entity.Users;
import com.hhplus.lecture.business.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<Users, Long>, UserRepository {
    @Override
    default Users save(Users users) {
        return save(users);
    }

    @Override
    default Users findByUserId(Long userId) {
        return findByUserId(userId);
    }
}
