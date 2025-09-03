package com.firstapp.app1.todo;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	


private static List<Todo> todos = new ArrayList<>();
	
	public static int TodoCount=0;

	static {
		todos.add(new Todo(++TodoCount, "user","Get AWS Certified 1", 
							LocalDate.now().plusYears(1), false ));
		todos.add(new Todo(++TodoCount, "user","Learn DevOps 1", 
				LocalDate.now().plusYears(2), false ));
		todos.add(new Todo(++TodoCount, "user","Learn Full Stack Development 1", 
				LocalDate.now().plusYears(3), false ));
	}
	

    public List<Todo> findByUsername(String Username){
				Predicate<? super Todo> Predicate =
				 todo -> todo.getUsername().equalsIgnoreCase(Username);

        return todos.stream().filter(Predicate).toList();
    }

	public void addTodo(String username,String description,LocalDate targeDate,boolean done){
		Todo todo =new Todo(++TodoCount, username, description, targeDate, done);
		todos.add(todo);
	}

	public void deleteById(int id){
		Predicate<? super Todo> Predicate = todo -> todo.getId()==id;
		todos.removeIf(Predicate);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> Predicate = todo -> todo.getId()==id;
		Todo todo=todos.stream().filter(Predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}