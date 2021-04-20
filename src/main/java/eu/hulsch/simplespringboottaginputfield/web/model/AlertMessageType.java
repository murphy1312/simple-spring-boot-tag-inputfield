package eu.hulsch.simplespringboottaginputfield.web.model;

public enum AlertMessageType {
    SUCCESS("alert-success"),
    INFO("alert-info"),
    WARNING("alert-warning"),
    DANGER("alert-danger");


    private String messageType;

    AlertMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }
}
