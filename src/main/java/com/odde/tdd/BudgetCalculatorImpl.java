/*
 * MicroFocus.com Inc.
 * Copyright(c) 2019 All Rights Reserved.
 */
package com.odde.tdd;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wusui
 * @version $Id: BudgetCalculatorImpl.java, 2019-11-11 4:09 PM wusui Exp $
 */
public class BudgetCalculatorImpl implements BudgetCalculator {

    BudgetRepo repo;

    Map<YearMonth, Budget> map = new HashMap<>();

    public BudgetCalculatorImpl(BudgetRepo repo) {
        this.repo = repo;
        this.repo.findAll().stream().forEach(budget -> map.put(budget.getYearMonth(), budget));
    }

    @Override
    public long calculateBudget(LocalDate start, LocalDate end) {
        YearMonth ymOfStart = YearMonth.of(start.getYear(), start.getMonth());
        YearMonth ymOfEnd = YearMonth.of(end.getYear(), end.getMonth());
        if (start.isAfter(end)) {
            // Invalid
            return 0;
        } else if (ymOfStart.equals(ymOfEnd)) {
            // Same month
            return getBudgetInMonth(start, end);
        } else {
            long total1 = getBudgetInMonth(start, ymOfStart.atEndOfMonth());
            long total3 = getBudgetInMonth(ymOfEnd.atDay(1), end);

            long total2 = 0;
            for (int year = start.getYear(); year <= end.getYear(); year++) {
                for (int month = 1; month <= 12; month++) {
                    YearMonth ym = YearMonth.of(year, month);
                    if (ym.isBefore(ymOfStart) || ym.equals(ymOfStart)) {
                        continue;
                    }
                    if (ym.isAfter(ymOfEnd) || ym.equals(ymOfEnd)) {
                        break;
                    }
                    if (map.containsKey(ym)) {
                        Budget budget = map.get(ym);
                        total2 += budget.getAmount();
                    }
                }
            }
            return total1 + total2 + total3;
        }
    }

    long getBudgetInMonth(LocalDate start, LocalDate end) {
        YearMonth ymOfStart = YearMonth.of(start.getYear(), start.getMonth());
        if (map.containsKey(ymOfStart)) {
            Budget budget = map.get(ymOfStart);
            int lengthOfMonth = ymOfStart.lengthOfMonth();

            Period period = Period.between(start, end);
            int length = period.getDays() + 1;
            return budget.getAmount() / lengthOfMonth * length;
        } else {
            return 0;
        }
    }
}
