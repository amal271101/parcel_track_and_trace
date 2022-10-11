package at.fhtw.swen3.persistence.entity;


    public enum StateEnum{
        PICKUP("Pickup"),
        INTRANSPORT("InTransport"),
        INTRUCKDELIVERY("InTruckDelivery"),
        TRANSFERRED("Transferred"),
        DELIVERED("Delivered");

        private String value;
        StateEnum (String value){this.value = value;}
    }
