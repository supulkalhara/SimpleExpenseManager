/*
 * Copyright 2015 Department of Computer Science and Engineering, University of Moratuwa.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *                  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package lk.ac.mrt.cse.dbs.simpleexpensemanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.ExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.PersistentDemoExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;

public class ApplicationTest {
    private ExpenseManager expenseManager;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        expenseManager = new PersistentDemoExpenseManager(context);
    }

    @Test
    public void testAddAccount() {
        expenseManager.addAccount("111222", "Hatton National Bank", "Kasun Kalhara", 200000.00);
        List<String> accountNumbers = expenseManager.getAccountNumbersList();
        assertTrue(accountNumbers.contains("111222"));

//        expenseManager.addAccount("12345A", "Yoda Bank", "Anakin Skywalker", 10000.0);
//        List<String> accountNumbers2 = expenseManager.getAccountNumbersList();
//        assertTrue(accountNumbers2.contains("12345A"));
    }

    @Test
    public void testGetAccount() throws InvalidAccountException {
        expenseManager.addAccount("111222", "Hatton National Bank", "Kasun Kalhara", 200000.00);
        Account account = expenseManager.getAccountsDAO().getAccount("111222");
        assertEquals("111222", account.getAccountNo());
        assertEquals("Kasun Kalhara", account.getAccountHolderName());
        assertEquals("Hatton National Bank", account.getBankName());
        assertEquals(200000.00, account.getBalance(),0.0);
    }
}