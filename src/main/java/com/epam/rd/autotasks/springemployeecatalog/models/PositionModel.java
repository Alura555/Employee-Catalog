package com.epam.rd.autotasks.springemployeecatalog.models;

public enum PositionModel {
    PRESIDENT,
    MANAGER,
    ANALYST,
    CLERK,
    SALESMAN;

    @Override
    public String toString() {
        switch (this) {
            case PRESIDENT:
                return "PRESIDENT";
            case MANAGER:
                return "MANAGER";
            case ANALYST:
                return "ANALYST";
            case CLERK:
                return "CLERK";
            case SALESMAN:
                return "SALESMAN";
        }
        return "";
    }
}
