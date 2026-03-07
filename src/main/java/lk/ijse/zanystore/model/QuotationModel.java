///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package lk.ijse.zanystore.model;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import lk.ijse.zanystore.db.DBConnection;
//import lk.ijse.zanystore.dto.QuotationItemDTO;
//import lk.ijse.zanystore.util.CrudUtil;
//
///**
// *
// * @author chathura
// */
//public class QuotationModel {
//
//    private QuotationItemModel quotationItemModel = new QuotationItemModel();
//    public boolean placeQuotation(QuotationItemDTO quotationItemDTO) throws SQLException{
//        Connection conn = DBConnection.getInstance().getConnection();
//        try{
//            conn.setAutoCommit(false);
//            boolean result1 = CrudUtil.execute("INSERT INTO quotation (customer_name, total_amount) VALUES (?,?)", quotationItemDTO.getCustomer_name(),quotationItemDTO.getFullTotal());
//
//            if(result1){
//                ResultSet result2 = CrudUtil.execute("SELECT quotation_id FROM quotation ORDER BY quotation_id DESC LIMIT 1");
//
//                if(result2.next()){
//                    int qId = result2.getInt("quotation_id");
//
//                    boolean result3 = quotationItemModel.saveQuotation(qId, quotationItemDTO.getItemList());
//                }
//                else{
//                    throw new SQLException();
//                }
//            }
//            conn.commit();
//        return true;
//        }
//        catch(Exception e){
//            conn.rollback();
//            throw e;
//        }
//        finally{
//            conn.setAutoCommit(true);
//        }
//
//    }
//}
