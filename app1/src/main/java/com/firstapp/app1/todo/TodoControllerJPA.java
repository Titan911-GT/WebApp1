package com.firstapp.app1.todo;

import java.time.LocalDate;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class TodoControllerJPA {

	public TodoControllerJPA(TodoRepo todoRepo) {
		super();
		
		this.todoRepo = todoRepo;
	}

	

	private TodoRepo todoRepo;


	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String user = GetLoggedInUsername(model);
		List<Todo> todos = todoRepo.findByUsername(user);
		model.addAttribute("todos", todos);
		return "listTodos";
	}

	@GetMapping("add-todos")
	public String showTodoPage(ModelMap model) {
		String user = GetLoggedInUsername(model);
		Todo todo = new Todo(0, user, "Default Description", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todos";
	}

	@PostMapping("add-todos")
	public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {

		String username=GetLoggedInUsername(model);
		todo.setUsername(username);
		todoRepo.save(todo);

		if (result.hasErrors()) {
			return "todos";
		}

		// String user = GetLoggedInUsername(model);
		// todoService.addTodo(user, todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todos")
	public String DeleteTodos(@RequestParam int id) {
		// Delete Todos
		todoRepo.deleteById(id);
		return "redirect:list-todos";
	}

	@GetMapping("update-todos")
	public String showUpdatePage(@RequestParam int id, ModelMap model) {
		Todo todo = todoRepo.findById(id).get();
		model.addAttribute("todo", todo); // 👈 required
		return "todos";
	}

	@PostMapping("update-todos")
	public String UpdateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todos"; // show the form again if errors
		}

		String user = GetLoggedInUsername(model);
		todo.setUsername(user);
		todoRepo.save(todo);
		// todoService.updateTodo(todo);

		return "redirect:list-todos"; // ✅ redirect to the list page
	}

	public String GetLoggedInUsername(ModelMap model){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    } 

}