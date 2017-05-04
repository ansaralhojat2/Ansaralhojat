package DTO;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class MeetingDTO implements Serializable {

    private Long id;
    private String date;
    private String decorum;
    private Integer pictureCount;
    private List<String> addresses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDecorum() {
        return decorum;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public void setDecorum(String decorum) {
        this.decorum = decorum;
    }

    public Integer getPictureCount() {
        return pictureCount;
    }

    public void setPictureCount(Integer pictureCount) {
        this.pictureCount = pictureCount;
    }


}
