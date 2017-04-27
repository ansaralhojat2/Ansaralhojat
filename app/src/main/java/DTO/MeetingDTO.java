package DTO;

public class MeetingDTO {

    private Long id;
    private String date;
    private String decorum;
    private Integer pictureCount;

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
