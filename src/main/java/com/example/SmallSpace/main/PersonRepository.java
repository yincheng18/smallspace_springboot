package com.example.SmallSpace.main;

import org.springframework.data.jpa.repository.JpaRepository;
public interface PersonRepository extends JpaRepository<Student,Integer> {
}
