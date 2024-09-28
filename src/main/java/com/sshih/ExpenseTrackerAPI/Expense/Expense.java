package com.sshih.ExpenseTrackerAPI.Expense;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sshih.ExpenseTrackerAPI.User.User;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="expenses")
public class Expense {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private Double amount;
    private String category;
    private String description;
    private LocalDateTime time;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Expense() {
        time = LocalDateTime.now();
    }
    public Expense(Double amount, String category, String description) {
        this.amount = amount;
        this.category = category;
        this.description = description;
    }
}
