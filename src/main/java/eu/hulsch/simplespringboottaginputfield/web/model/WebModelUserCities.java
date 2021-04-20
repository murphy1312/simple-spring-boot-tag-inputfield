package eu.hulsch.simplespringboottaginputfield.web.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class WebModelUserCities {

    public static final String WEB_MODEL_USER_CITIES = "web_model_user_cities";

    @NotEmpty(message = "Username cannot be empty")
    @Size(message = "Username must be between 3 and 15 characters", min = 3, max = 15)
    private String username;

    private List<String> cityNames;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getCityNames() {
        return cityNames;
    }

    public void setCityNames(List<String> cityNames) {
        this.cityNames = cityNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebModelUserCities)) return false;
        WebModelUserCities that = (WebModelUserCities) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(cityNames, that.cityNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, cityNames);
    }

    @Override
    public String toString() {
        return "WebModelUserCities{" +
                "username='" + username + '\'' +
                ", cityNames=" + cityNames +
                '}';
    }
}
