/*
 * MicroFocus.com Inc.
 * Copyright(c) 2019 All Rights Reserved.
 */
package com.odde.tdd;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author wusui
 * @version $Id: BudgetCalculator.java, 2019-11-11 4:06 PM wusui Exp $
 */
public interface BudgetCalculator {

    long calculateBudget(LocalDate start, LocalDate end);

}
