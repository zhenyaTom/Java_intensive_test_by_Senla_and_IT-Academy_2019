package com.senla.atm.api.facade;

import java.util.Date;

public interface IAtm {
    Boolean checkCardExistence (String cardNumber);
    Boolean checkCardIsLocked ();
    Date getUnlockDate ();
    Boolean logIn (Integer pinCode);
    Integer getWrongPinCodeCounter();
    Boolean getUserIsAuthorized();
    Double checkBalance ();
    Double getAvailableCash ();
    Double getTopUpLimit();
    int giveCash(Double amount);
    int topUpBalance(Double amount);
    void logOut ();
}