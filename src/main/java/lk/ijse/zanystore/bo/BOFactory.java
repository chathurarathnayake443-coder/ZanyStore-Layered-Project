//package lk.ijse.zanystore.bo;
//
//public class BOFactory {
//    private static BOFactory instance;
//    private BOFactory(){}
//    public static BOFactory getInstance() {
//        return instance==null?instance=new BOFactory():instance;
//    }
//    public enum BOTypes{
//        CUSTOMER,ITEM,PLACE_ORDER
//    }
//    public SuperBO getBOFactory(BOTypes boType){
//        switch (boType){
//            case CUSTOMER:
//                return new CustomerBOImpl();
//            case ITEM:
//                return new ItemBOImpl();
//            case PLACE_ORDER:
//                return new PlaceOrderBoImpl();
//            default:
//                return null;
//        }
//    }
//
//}
