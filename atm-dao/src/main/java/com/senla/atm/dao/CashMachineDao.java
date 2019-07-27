package com.senla.atm.dao;

import com.senla.atm.api.dao.ICashMachineDao;
import com.senla.atm.dao.utils.EntityParser;
import com.senla.atm.dao.utils.FileWorker;
import com.senla.atm.entity.CashMachine;

import java.io.FileNotFoundException;

public class CashMachineDao implements ICashMachineDao{
    private static CashMachineDao instance;
    private final String CASH_MACHINE_FILE_PATH = "cashMachine.txt";


    private CashMachineDao(){
    }

    public static CashMachineDao getInstance() {
        if (instance == null) {
            instance = new CashMachineDao();
        }
        return instance;
    }

    public CashMachine getCashMachine() throws FileNotFoundException {
        return EntityParser.parseCashMachineFromString(FileWorker.readCashMachineFromFile(CASH_MACHINE_FILE_PATH));
    }

    public void updateCashMachine (CashMachine cashMachine) throws FileNotFoundException {
        FileWorker.writeCashMachineToFile(cashMachine, CASH_MACHINE_FILE_PATH);
    }
}