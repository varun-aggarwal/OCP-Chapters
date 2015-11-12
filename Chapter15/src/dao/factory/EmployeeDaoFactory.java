package dao.factory;

import dao.EmployeeDao;
import dao.impl.EmployeeDaoConnectedRowSet;
import dao.impl.EmployeeDaoNormalCursorImpl;

public class EmployeeDaoFactory {

    public EmployeeDao getEmployeeDao(String implType) {

        if (implType.equalsIgnoreCase("CONNECTED")) {
            return new EmployeeDaoConnectedRowSet();
        }
        return new EmployeeDaoNormalCursorImpl();
    }

}
