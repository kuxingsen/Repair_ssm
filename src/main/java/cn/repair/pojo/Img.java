package cn.repair.pojo;

public class Img{
    private int id;
    private String title;
    private int type;
    private String topImage;

    public Img() {
    }

    @Override
    public String toString() {
        return "Img{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", topImage='" + topImage + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTopImage() {
        return topImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage;
    }
}
