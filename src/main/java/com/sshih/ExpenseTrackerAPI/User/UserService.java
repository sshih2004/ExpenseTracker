package com.sshih.ExpenseTrackerAPI.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshih.ExpenseTrackerAPI.Expense.Expense;
import com.sshih.ExpenseTrackerAPI.Expense.ExpenseRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Expense addExpenseToUser(Long userId, Expense expense) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        expenseRepository.save(expense);
        user.addExpense(expense);
        userRepository.save(user); // Due to CascadeType.ALL, expense will be saved

        return expense;
    }

    public List<Expense> getUserExpenses(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            return userRepository.findById(userId).get().getExpenses();
        }
        throw new RuntimeException("can't find user");
    }

    // Other methods (updateUser, deleteUser, etc.)
}