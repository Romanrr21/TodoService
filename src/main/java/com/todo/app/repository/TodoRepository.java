package com.todo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.app.domain.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

	
}
