package com.senla.atm.service;

import com.senla.atm.api.dao.ICashMachineDao;
import com.senla.atm.api.service.ICashMachineService;
import com.senla.atm.dao.CashMachineDao;
import com.senla.atm.entity.CashMachine;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;

public class CashMachineService implements ICashMachineService {
    private static Logger logger = Logger.getLogger(CashMachineService.class);
    private static CashMachineService instance;
    private ICashMachineDao cashMachineDao = CashMachineDao.getInstance();

    private CashMachineService(){
    }

    public static CashMachineService getInstance() {
        if (instance == null) {
            instance = new CashMachineService();
        }
        return instance;
    }

    public Double getAvailableCash (){
        try {
            return cashMachineDao.getCashMachine().getAvailableCash();
        } catch (FileNotFoundException e) {
            logger.error("Cash machine file not found!", e);
            return null;
        }
    }

    public Double getTopUpLimit (){
        try {
            return cashMachineDao.getCashMachine().getTopUpLimit();
        } catch (FileNotFoundException e) {
            logger.error("Cash machine file not found!", e);
            return null;
        }
    }

    public void increaseAvailableCash (Double amount) throws FileNotFoundException {
        CashMachine cashMachine = cashMachineDao.getCashMachine();
        cashMachine.setAvailableCash(cashMachine.getAvailableCash() + amount);
        cashMachineDao.updateCashMachine(cashMachine);
    }

    public void reduceAvailableCash (Double amount) throws FileNotFoundException {
        CashMachine cashMachine = cashMachineDao.getCashMachine();
        cashMachine.setAvailableCash(cashMachine.getAvailableCash() - amount);
        cashMachineDao.updateCashMachine(cashMachine);
    }
}