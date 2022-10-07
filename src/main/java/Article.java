public class Article {
    private String headline;
    private String img;
    private String content;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "headline='" + headline + '\'' +
                ", img='" + img + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
