package lk.ijse.zanystore.bo;

import lk.ijse.zanystore.bo.custom.SuperBO;
import lk.ijse.zanystore.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory instance;
    private BOFactory(){}
    public static BOFactory getInstance() {
        return instance==null?instance=new BOFactory():instance;
    }
    public enum BOTypes{
        CUSTOMER,ITEM,PLACE_ORDER,QUOTATION,EMPLOYEE,LAYOUT,PAYMENT,RETURN,SERVICEPROVIDER,SUPPLIER,USER,QUERY
    }
    public SuperBO getBOFactory(BOTypes boType){
        switch (boType){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            case QUOTATION:
                return new CreateQuotationBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case LAYOUT:
                return new LayoutBOImpl();
            case QUERY:
                return new QueryBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case RETURN:
                return new ReturnBOImpl();
            case SERVICEPROVIDER:
                return new ServiceProviderBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }

}
