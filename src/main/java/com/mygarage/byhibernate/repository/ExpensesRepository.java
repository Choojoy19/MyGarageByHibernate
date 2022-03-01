package com.mygarage.byhibernate.repository;

import com.mygarage.byhibernate.model.Expenses;

import java.time.LocalDate;

public interface ExpensesRepository extends BaseRepository <Expenses> {
    String sumExpense (LocalDate fromDate, LocalDate toDate, String id, String typeOfExpense);

}
