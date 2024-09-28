package com.sshih.ExpenseTrackerAPI.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sshih.ExpenseTrackerAPI.Expense.Expense;

import lombok.extern.java.Log;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Log
@Controller
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
    @ResponseBody
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
    public String getUserExpenses(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId);
        if (user == null) {
            return "user_not_found";
        }
        model.addAttribute("user", user);
        return "user_profile";
    }

    // Other endpoints (getUserById, updateUser, deleteUser, etc.)
}
