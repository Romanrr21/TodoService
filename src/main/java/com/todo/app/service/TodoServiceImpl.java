package com.todo.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.todo.app.domain.Todo;
import com.todo.app.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {
	private final static Log logger = LogFactory.getLog(TodoServiceImpl.class);
	@Autowired
	TodoRepository todoRepository;

	@Override
	public List<Todo> getAllTodos(Long userId) {
		logger.info("Fetching Todo list of user with userid=" + userId);
		Todo todo = new Todo();
		todo.setUserId(userId);
		Example<Todo> todoExample = Example.of(todo);
		List<Todo> todoList = todoRepository.findAll(todoExample);
		logger.info("number of todos=" + todoList.size());
		logger.info("Returning Todo list of user with userid=" + userId);

		return todoList;

	}

	@Override
	public void add(Todo todo) {
		logger.info("Adding todo in service");
		todo.setCreatedDate(new Date());
		todo.setStatus("pending");
		todoRepository.save(todo);
		logger.info("Added todo in service");

	}

	@Override
	public void update(Todo todo) {
		logger.info("updating todo in service");
		Long todoId = todo.getId();
		String todoName = todo.getName();
		String todoStatus = todo.getStatus();

		Optional<Todo> todoOptional = todoRepository.findById(todoId);
		try {
			Todo t = todoOptional.get();
			if (!StringUtils.isEmpty(todoName)) {
				t.setName(todoName);
			}
			if (!StringUtils.isEmpty(todoStatus)) {
				t.setStatus(todoStatus);
			}
			t.setLastUpdatedDate(new Date());
			todoRepository.save(t);

		} catch (Exception e) {
			logger.error("unable to find todo with id=" + todoId);

		}

	}

	@Override
	public void deleteById(Long todoId) {
		logger.info("Deleting todo in service");
		todoRepository.deleteById(todoId);
		logger.info("The deleted id is=" + todoId);
	}

}
