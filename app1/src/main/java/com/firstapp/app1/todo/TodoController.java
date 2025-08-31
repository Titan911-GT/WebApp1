package com.firstapp.app1.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

// import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoController {

	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	private TodoService todoService;

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		List<Todo> todos = todoService.findByUsername("in28minutes");
		model.addAttribute("todos", todos);
		return "listTodos";
	}

	@GetMapping("add-todos")
	public String showTodoPage(ModelMap model) {
		String user = (String) model.get("name");
		Todo todo = new Todo(0, user, "Default Description", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todos";
	}

	@PostMapping("add-todos")
	public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todos";
		}

		String user = (String) model.get("name");
		todoService.addTodo(user, todo.getDescription(), LocalDate.now().plusYears(1), false);
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todos")
	public String DeleteTodos(@RequestParam int id) {
		// Delete Todos
		todoService.deleteById(id);
		return "redirect:list-todos";
	}

	@RequestMapping("update-todos")
	public String showUpdatePage(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.findById(id);
		model.addAttribute("todo", todo); // 👈 required
		return "todos";
	}

}