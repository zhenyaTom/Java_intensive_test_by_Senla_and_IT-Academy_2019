package com.senla.atm.api.dao;

import com.senla.atm.entity.CashMachine;
import java.io.FileNotFoundException;

public interface ICashMachineDao {
    CashMachine getCashMachine() throws FileNotFoundException;
    void updateCashMachine (CashMachine cashMachine) throws FileNotFoundException;
}