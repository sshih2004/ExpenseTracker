package com.sshih.ExpenseTrackerAPI.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @PostMapping("/expenses")
    public String addExpenseToUser(@ModelAttribute Expense expense, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new_expense";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        userService.addExpenseToUser(user.getId(), expense);
        model.addAttribute("user", user);
        return "user_profile";
    }

    // Get all expenses for a user
    @GetMapping("/expenses")
    public String getUserExpenses(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        if (user == null) {
            return "user_not_found";
        }
        model.addAttribute("user", user);
        return "user_profile";
    }

    @GetMapping("/expenses/new")
    public String showCreateForm(Model model) {
        model.addAttribute("expense", new Expense());
        model.addAttribute("formActionUrl", "/users/expenses");
        return "new_expense";
    }


    @PostMapping("/expenses/{expenseId}")
    public String deleteExpenseFromUser(@PathVariable Long expenseId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.deleteExpenseFromUser(userService.findByUsername(username).getId(), expenseId);
        model.addAttribute("user", user);
        return "redirect:/users/expenses";
    }
}
