package com.senla.atm.ui.actions;

import com.senla.atm.facade.Atm;

public class ExitProgram implements IAction {
    public void execute(){
        Atm.getInstance().logOut();
    }
}