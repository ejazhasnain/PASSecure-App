package nnk.com.passecure;

public class cardView {
    private int img;
    private String title;
    public cardView()
    {

    }

    public cardView(int img, String title) {
        this.img = img;
        this.title = title;
    }


    public String getTitle() {
        return title;
    }


    public int getImg() {
        return img;
    }
}
