package com.mygarage.byhibernate.model;

import javax.persistence.*;
//import java.time.LocalDate;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date; //LocalDate
    private String typeOfExpense;
    private int price;
    private String commentExp;

    public Expenses(){}

    public Expenses (LocalDate date, String typeOfExpense, int price, String commentExp){
        this.date = date;
        this.typeOfExpense = typeOfExpense;
        this.price = price;
        this.commentExp = commentExp;
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



}
