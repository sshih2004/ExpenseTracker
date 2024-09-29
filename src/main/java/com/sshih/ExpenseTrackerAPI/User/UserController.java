package com.sshih.ExpenseTrackerAPI.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.sshih.ExpenseTrackerAPI.Expense.Expense;

import lombok.extern.java.Log;

import java.util.List;


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
    public String addExpenseToUser(@PathVariable Long userId, @ModelAttribute Expense expense, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new_expense";
        }
        userService.addExpenseToUser(userId, expense);
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "user_profile";
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

    @GetMapping("/{userId}/expenses/new")
    public String showCreateForm(@PathVariable Long userId, Model model) {
        model.addAttribute("expense", new Expense());
        model.addAttribute("formActionUrl", "/users/" + userId + "/expenses");
        return "new_expense";
    }


    @PostMapping("/{userId}/expenses/{expenseId}")
    public String deleteExpenseFromUser(@PathVariable Long userId, @PathVariable Long expenseId, Model model) {
        User user = userService.deleteExpenseFromUser(userId, expenseId);
        model.addAttribute("user", user);
        return "redirect:/users/" + userId + "/expenses";
    }
}
