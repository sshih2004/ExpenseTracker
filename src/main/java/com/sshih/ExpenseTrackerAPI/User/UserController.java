package com.sshih.ExpenseTrackerAPI.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sshih.ExpenseTrackerAPI.Expense.Expense;

import lombok.extern.java.Log;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Log
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    
    // Add an expense to a user
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}/expenses")
    public Expense addExpenseToUser(@PathVariable Long userId, @RequestBody Expense expense) {
        log.info(expense.getDescription());
        return userService.addExpenseToUser(userId, expense);
    }

    // Get all expenses for a user
    @GetMapping("/{userId}/expenses")
    public List<Expense> getUserExpenses(@PathVariable Long userId) {
        return userService.getUserExpenses(userId);
    }

    // Other endpoints (getUserById, updateUser, deleteUser, etc.)
}
