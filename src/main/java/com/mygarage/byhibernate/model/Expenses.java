package com.mygarage.byhibernate.model;

import javax.persistence.*;
//import java.time.LocalDate;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private String typeOfExpense;
    private int price;
    private String commentExp;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;



    public Expenses(){}

    public Expenses (LocalDate date, String typeOfExpense, int price, String commentExp){
        this.date = date;
        this.typeOfExpense = typeOfExpense;
        this.price = price;
        this.commentExp = commentExp;
    }

    public Car getCar() {
        return car;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTypeOfExpense() {
        return typeOfExpense;
    }

    public void setTypeOfExpense(String typeOfExpense) {
        this.typeOfExpense = typeOfExpense;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCommentExp() {
        return commentExp;
    }

    public void setCommentExp(String comment) {
        this.commentExp = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expenses expenses = (Expenses) o;
        return id == expenses.id && price == expenses.price && Objects.equals(date, expenses.date) && Objects.equals(typeOfExpense, expenses.typeOfExpense) && Objects.equals(commentExp, expenses.commentExp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, typeOfExpense, price, commentExp);
    }
}
