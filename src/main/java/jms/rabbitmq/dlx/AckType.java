package jms.rabbitmq.dlx;

public enum AckType {
    ACK("ack"), NACK("nack"), REJECT("reject"), NONE("");

    private String value;

    AckType(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
