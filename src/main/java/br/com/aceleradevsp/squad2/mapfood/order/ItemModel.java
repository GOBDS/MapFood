package br.com.aceleradevsp.squad2.mapfood.order;


public class ItemModel {

    private String itemId;
    private String itemDescription;
    private String classification;
    private double unitPrice;

    public ItemModel() {

    }

    public ItemModel(String itemId, String itemDescription, String classification, double unitPrice) {
        this.itemId = itemId;
        this.itemDescription = itemDescription;
        this.classification = classification;
        this.unitPrice = unitPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public static ItemModelBuilder builder() {
        return new ItemModelBuilder();
    }


    public static class ItemModelBuilder {
        private String itemId;
        private String itemDescription;
        private String classification;
        private double unitPrice;

        @Override
        public String toString() {
            return "ItemModelBuilder{" +
                    "itemId='" + itemId + '\'' +
                    ", itemDescription='" + itemDescription + '\'' +
                    ", classification='" + classification + '\'' +
                    ", unitPrice=" + unitPrice +
                    '}';
        }

        public ItemModelBuilder withItemId(String itemId) {

            this.itemId = itemId;
            return this;
        }

        public ItemModelBuilder withItemDescription(String itemDescription) {
            this.itemDescription = itemDescription;
            return this;
        }

        public ItemModelBuilder withClassification(String classification) {
            this.classification = classification;
            return this;
        }

        public ItemModelBuilder withUnitPrice(double unitPrice) {

            this.unitPrice = unitPrice;
            return this;
        }

        public ItemModel build() {
            return new ItemModel(itemId, itemDescription, classification, unitPrice);
        }
    }
}
