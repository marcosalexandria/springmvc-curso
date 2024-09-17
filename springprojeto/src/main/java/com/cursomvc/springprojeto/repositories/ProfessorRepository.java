package com.cursomvc.springprojeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursomvc.springprojeto.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{

}
