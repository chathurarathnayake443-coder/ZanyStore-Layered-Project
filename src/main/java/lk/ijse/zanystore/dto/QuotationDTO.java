package lk.ijse.zanystore.dto;

import java.text.DecimalFormat;

public class QuotationDTO {

    private int qId;
    private String itemName;
    private String color;
    private double unitPrice;
    private double qty;
    private double lineTotal;

    private static final DecimalFormat moneyFmt = new DecimalFormat("#,##0.00");

    public QuotationDTO(int qId, String itemName, String color, double unitPrice, double qty, double lineTotal) {
        this.qId = qId;
        this.itemName = itemName;
        this.color = color;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.lineTotal = lineTotal;
    }

    public int getqId() {
        return qId;
    }
    public void setqId(int qId) {}

    public QuotationDTO(String itemName, String color, double unitPrice, double qty, double lineTotal) {
        this.itemName = itemName;
        this.color = color;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.lineTotal = lineTotal;
    }
    
    

    public QuotationDTO(String itemName, String color, double unitPrice, double qty) {
        this.itemName = itemName;
        this.color = color;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.lineTotal = unitPrice * qty;
    }

    public String toDisplayString() {
        return itemName + " | " + color + " | " + qty +
                " × " + moneyFmt.format(unitPrice) + " = " + moneyFmt.format(lineTotal);
    }

    public String toFormattedText() {
        return String.format("%s  |  Color: %s  |  Qty: %s  |  Unit: %s  |  Total: %s",
                itemName, color, stripIfInteger(qty),
                moneyFmt.format(unitPrice),
                moneyFmt.format(lineTotal));
    }

    private String stripIfInteger(double v){
        if (v == (long) v) return String.valueOf((long)v);
        return String.valueOf(v);
    }

    // ===== getters and setters =====

    public String getItemName() { return itemName; }
    public String getColor() { return color; }
    public double getUnitPrice() { return unitPrice; }
    public double getQty() { return qty; }
    public double getLineTotal() { return lineTotal; }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }
    
    

    public void setQty(double qty) {
        this.qty = qty;
        this.lineTotal = qty * this.unitPrice;
    }
}


