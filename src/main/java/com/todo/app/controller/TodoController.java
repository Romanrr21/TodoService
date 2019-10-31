package com.todo.app.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.app.domain.Todo;
import com.todo.app.service.TodoServiceImpl;

@RestController
@RequestMapping("/todo")
public class TodoController {

	private final static Log logger = LogFactory.getLog(TodoController.class);

	@Autowired
	TodoServiceImpl todoService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> getAllTodos(@RequestParam("userId") Long userId) {
		List<Todo> todoList = todoService.getAllTodos(userId);
		if (todoList != null && !todoList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(todoList);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(todoList);
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Todo todo) {
		logger.info("Adding todo");
		todoService.add(todo);
		logger.info("Todo Added");
		return ResponseEntity.status(HttpStatus.OK).body("todo is added");
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody Todo todo){
		logger.info("updating todo");
		todoService.update(todo);
		logger.info("Todo Updated");
		return ResponseEntity.status(HttpStatus.OK).body("todo is updated");
		
	}
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public void deleteById(@PathVariable Long id) {
		todoService.deleteById(id);
		
	}
}

