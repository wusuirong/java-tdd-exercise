/*
 * MicroFocus.com Inc.
 * Copyright(c) 2019 All Rights Reserved.
 */
package com.odde.tdd;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wusui
 * @version $Id: BudgetRepoMock.java, 2019-11-11 4:19 PM wusui Exp $
 */
public class BudgetRepoMock implements BudgetRepo {

    private List<Budget> budgets = new ArrayList<Budget>();

    public void add(Budget budget) {
        budgets.add(budget);
    }

    public List<Budget> findAll() {
        return budgets;
    }
}
