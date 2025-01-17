package ru.vasilev.securityexample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.vasilev.securityexample.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public Optional<User> findByUsername(String name);
}