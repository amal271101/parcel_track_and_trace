package at.fhtw.swen3.services.dto;

import at.fhtw.swen3.persistence.Recipient;

public class ParcelDto {
    public ParcelDto(Float weight, Recipient recipient, Recipient sender) {
        this.weight = weight;
        this.recipient = recipient;
        this.sender = sender;
    }

    public ParcelDto() {
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    private Float weight;

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
