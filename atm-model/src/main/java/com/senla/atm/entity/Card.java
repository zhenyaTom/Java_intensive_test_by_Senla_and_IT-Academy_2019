package com.senla.atm.entity;

import com.senla.atm.entity.utils.DateUtil;
import java.util.Date;

public class Card {
    private String number;
    private Integer pinCode;
    private Double balance;
    private Boolean isLocked;
    private Date lockDate;
    private Integer wrongPinCodeCounter;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean locked) {
        isLocked = locked;
    }

    public Date getLockDate() {
        return lockDate;
    }

    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }

    public Integer getWrongPinCodeCounter() {
        return wrongPinCodeCounter;
    }

    public void setWrongPinCodeCounter(Integer wrongPinCodeCounter) {
        this.wrongPinCodeCounter = wrongPinCodeCounter;
    }

    @Override
    public String toString (){
        StringBuilder stringBuilder = new StringBuilder();
        String separator = " ";
        stringBuilder.append(number).append(separator);
        stringBuilder.append(pinCode).append(separator);
        stringBuilder.append(balance).append(separator);
        stringBuilder.append(isLocked).append(separator);
        stringBuilder.append(DateUtil.convertDateToString(lockDate)).append(separator);
        stringBuilder.append(wrongPinCodeCounter).append(separator);
        return stringBuilder.toString();
    }
}