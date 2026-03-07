/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.model;

import java.sql.SQLException;
import java.util.List;
import lk.ijse.zanystore.dto.QuotationDTO;
import lk.ijse.zanystore.util.CrudUtil;

/**
 *
 * @author chathura
 */
public class QuotationItemModel {
    public boolean saveQuotation(int qId, List<QuotationDTO> itemList){
        try{

            int quotationId = qId;

            for(QuotationDTO quotationDTO : itemList){
                boolean result = CrudUtil.execute("INSERT INTO quotation_item (quotation_id,item_name, color,quantity,unit_price) VALUES (?,?,?,?,?)", quotationId, quotationDTO.getItemName(),quotationDTO.getColor(),quotationDTO.getQty(),quotationDTO.getUnitPrice());

                if(!result){
                    throw new SQLException();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
