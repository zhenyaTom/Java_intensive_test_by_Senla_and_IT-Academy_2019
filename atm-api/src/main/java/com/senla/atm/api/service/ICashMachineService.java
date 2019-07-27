package com.senla.atm.api.service;

import java.io.FileNotFoundException;

public interface ICashMachineService {
    Double getAvailableCash ();
    Double getTopUpLimit();
    void increaseAvailableCash (Double amount) throws FileNotFoundException;
    void reduceAvailableCash (Double amount) throws FileNotFoundException;
}