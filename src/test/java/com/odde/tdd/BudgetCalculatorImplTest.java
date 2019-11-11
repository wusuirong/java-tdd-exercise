/*
 * MicroFocus.com Inc.
 * Copyright(c) 2019 All Rights Reserved.
 */
package com.odde.tdd;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wusui
 * @version $Id: BudgetCalculatorImplTest.java, 2019-11-11 4:10 PM wusui Exp $
 */
public class BudgetCalculatorImplTest {

    LocalDate getDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }

    @Test
    public void calculteNoDay() {
        BudgetRepoMock repo = new BudgetRepoMock();
        repo.add(new Budget(YearMonth.of(2019, 9), 30));

        LocalDate start = getDate(2019, 9, 2);
        LocalDate end = getDate(2019, 9, 1);

        BudgetCalculatorImpl target = new BudgetCalculatorImpl(repo);
        long total = target.calculateBudget(start, end);

        Assert.assertEquals(0, total);
    }

    @Test
    public void calculteOneDay() {
        BudgetRepoMock repo = new BudgetRepoMock();
        repo.add(new Budget(YearMonth.of(2019, 9), 30));

        LocalDate start = getDate(2019, 9, 1);
        LocalDate end = getDate(2019, 9, 1);

        BudgetCalculatorImpl target = new BudgetCalculatorImpl(repo);
        long total = target.calculateBudget(start, end);

        Assert.assertEquals(1, total);
    }

    @Test
    public void calculteOneMonth() {
        BudgetRepoMock repo = new BudgetRepoMock();
        repo.add(new Budget(YearMonth.of(2019, 9), 30));

        LocalDate start = getDate(2019, 9, 1);
        LocalDate end = getDate(2019, 9, 30);

        BudgetCalculatorImpl target = new BudgetCalculatorImpl(repo);
        long total = target.calculateBudget(start, end);

        Assert.assertEquals(30, total);
    }

    @Test
    public void calculteTwoMonth() {
        BudgetRepoMock repo = new BudgetRepoMock();
        repo.add(new Budget(YearMonth.of(2019, 9), 30));
        repo.add(new Budget(YearMonth.of(2019, 10), 31*2));

        LocalDate start = getDate(2019, 9, 1);
        LocalDate end = getDate(2019, 10, 31);

        BudgetCalculatorImpl target = new BudgetCalculatorImpl(repo);
        long total = target.calculateBudget(start, end);

        Assert.assertEquals(30+31*2, total);
    }

    @Test
    public void calculteOneMonthAndOneDay() {
        BudgetRepoMock repo = new BudgetRepoMock();
        repo.add(new Budget(YearMonth.of(2019, 9), 30));
        repo.add(new Budget(YearMonth.of(2019, 10), 31*2));

        LocalDate start = getDate(2019, 9, 1);
        LocalDate end = getDate(2019, 10, 1);

        BudgetCalculatorImpl target = new BudgetCalculatorImpl(repo);
        long total = target.calculateBudget(start, end);

        Assert.assertEquals(30+2, total);
    }

    @Test
    public void calculteOneDayAndOneMonth() {
        BudgetRepoMock repo = new BudgetRepoMock();
        repo.add(new Budget(YearMonth.of(2019, 9), 30));
        repo.add(new Budget(YearMonth.of(2019, 10), 31*2));

        LocalDate start = getDate(2019, 9, 30);
        LocalDate end = getDate(2019, 10, 31);

        BudgetCalculatorImpl target = new BudgetCalculatorImpl(repo);
        long total = target.calculateBudget(start, end);

        Assert.assertEquals(1+31*2, total);
    }

    @Test
    public void calculteNearlyTwoMonth() {
        BudgetRepoMock repo = new BudgetRepoMock();
        repo.add(new Budget(YearMonth.of(2019, 9), 30));
        repo.add(new Budget(YearMonth.of(2019, 10), 31*2));

        LocalDate start = getDate(2019, 9, 3);
        LocalDate end = getDate(2019, 10, 2);

        BudgetCalculatorImpl target = new BudgetCalculatorImpl(repo);
        long total = target.calculateBudget(start, end);

        Assert.assertEquals(30-2+2*2, total);
    }

    @Test
    public void calculteSepToMar() {
        BudgetRepoMock repo = new BudgetRepoMock();
        repo.add(new Budget(YearMonth.of(2019, 9), 30));
        repo.add(new Budget(YearMonth.of(2019, 10), 31*2));
        repo.add(new Budget(YearMonth.of(2019, 11), 30*3));
        repo.add(new Budget(YearMonth.of(2019, 12), 31*4));
        repo.add(new Budget(YearMonth.of(2020, 1), 31*5));
        repo.add(new Budget(YearMonth.of(2020, 2), 29*6));
        repo.add(new Budget(YearMonth.of(2020, 3), 31*7));

        LocalDate start = getDate(2019, 9, 1);
        LocalDate end = getDate(2020, 3, 31);

        BudgetCalculatorImpl target = new BudgetCalculatorImpl(repo);
        long total = target.calculateBudget(start, end);

        Assert.assertEquals(30+31*2+30*3+31*4+31*5+29*6+31*7, total);
    }

    @Test
    public void calculteSepToMarV2() {
        BudgetRepoMock repo = new BudgetRepoMock();
        repo.add(new Budget(YearMonth.of(2019, 9), 30));
        repo.add(new Budget(YearMonth.of(2019, 10), 31*2));
        repo.add(new Budget(YearMonth.of(2019, 11), 30*3));
        repo.add(new Budget(YearMonth.of(2019, 12), 31*4));
        repo.add(new Budget(YearMonth.of(2020, 1), 31*5));
        repo.add(new Budget(YearMonth.of(2020, 2), 29*6));
        repo.add(new Budget(YearMonth.of(2020, 3), 31*7));

        LocalDate start = getDate(2019, 9, 3);
        LocalDate end = getDate(2020, 3, 28);

        BudgetCalculatorImpl target = new BudgetCalculatorImpl(repo);
        long total = target.calculateBudget(start, end);

        Assert.assertEquals(30+31*2+30*3+31*4+31*5+29*6+31*7-2-21, total);
    }

}
