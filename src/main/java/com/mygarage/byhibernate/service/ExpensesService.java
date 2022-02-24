package com.mygarage.byhibernate.service;

import com.mygarage.byhibernate.model.Expenses;

import java.time.LocalDate;

public interface ExpensesService extends BaseService<Expenses> {
    String sumExpense (LocalDate fromDate, LocalDate toDate, String id, String typeOfExpense);
}
