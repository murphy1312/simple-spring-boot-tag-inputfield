package eu.hulsch.simplespringboottaginputfield.web.model;

import java.io.Serializable;

import static eu.hulsch.simplespringboottaginputfield.web.model.AlertMessageType.*;


public class AlertMessage implements Serializable {
    public static final String ALERT_MODEL_NAME = "alertMessage";
    private String message;
    private String type;

    private AlertMessage() {
    }

    private AlertMessage(String message, AlertMessageType type) {
        this.message = message;
        this.type = type.getMessageType();
    }

    public static AlertMessage build(String message, AlertMessageType type) {
        return new AlertMessage(message, type);
    }

    public static AlertMessage success(String message) {
        return new AlertMessage(message, AlertMessageType.SUCCESS);
    }

    public static AlertMessage info(String message) {
        return new AlertMessage(message, INFO);
    }

    public static AlertMessage warning(String message) {
        return new AlertMessage(message, WARNING);
    }

    public static AlertMessage danger(String message) {
        return new AlertMessage(message, DANGER);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
