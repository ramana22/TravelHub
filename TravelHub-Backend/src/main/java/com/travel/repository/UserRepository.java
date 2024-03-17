package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    public User findByEmail(String email);
    public  User findByEmailAndPassword(String email,String password);
}
