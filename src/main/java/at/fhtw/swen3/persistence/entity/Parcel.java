package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.persistence.Recipient;

public class Parcel {
    private Float weight;

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Recipient getSender() {
        return sender;
    }

    public void setSender(Recipient sender) {
        this.sender = sender;
    }

    private Recipient recipient;
    private Recipient sender;


}
