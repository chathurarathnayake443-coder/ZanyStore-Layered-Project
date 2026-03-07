package lk.ijse.zanystore.dao;

import lk.ijse.zanystore.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory instance;
    private  DAOFactory() {}
    public static DAOFactory getInstance() {
        return instance == null ? instance = new DAOFactory() : instance;
    }
    public enum DAOTypes {
        CUSTOMER,
        ITEM,
        CLOTH_ORDER,
        CLOTH_ORDER_DETAIL,
        QUERY,
        EMPLOYEE,
        ITEM_COLOR_STOCK,
        CLOTH_ORDER_PAYMENT,
        CLOTH_ORDER_SERPROVIDER,
        PAYMENT,
        QUOTATION,
        QUOTATION_ITEM,
        RETURN,
        RETURN_DETAIL,
        SERVICEPROVIDER,
        SUPPLIER,
        TASK,
        USER
    }
    public SuperDAO getDAO(DAOTypes daoType){
        switch (daoType){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case CLOTH_ORDER:
                return new ClothOrderDAOImpl();
            case CLOTH_ORDER_DETAIL:
                return new ClothOrderDetailDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ITEM_COLOR_STOCK:
                return new ItemColorStockDAOImpl();
            case CLOTH_ORDER_PAYMENT:
                return new OrderPaymentDAOImpl();
            case CLOTH_ORDER_SERPROVIDER:
                return new OrderSerproviderDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case QUOTATION:
                return new QuotationDAOImpl();
            case QUOTATION_ITEM:
                return new QuotationItemDAOImpl();
            case RETURN:
                return new ReturnDAOImpl();
            case RETURN_DETAIL:
                return new ReturnDetailDAOImpl();
            case SERVICEPROVIDER:
                return new ServiceproviderDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case TASK:
                return new TaskDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }

}
