package com.todo.app.service;

import java.util.List;

import com.todo.app.domain.Todo;

public interface TodoService {

	public List<Todo> getAllTodos(Long userId);
	public void add (Todo todo);
	public void update(Todo todo);
	public void deleteById( Long todoId);
}
