public class Rectangle {
    private int recwidth;
    private int recheight;

    public Rectangle(int w, int h) {
        setW(w);
        setH(h);
    }

    public int getW() {
        return recwidth;
    }

    public void setW(int w) {
        recwidth = w;
    }

    public int getH() {
        return recheight;
    }

    public void setH(int h) {
        recheight = h;
    }

    public String showRectangle() {
        String rec = "";
        rec = "Rectangular area is " + (getW() * getH()) +
                "\n" + "Perimeter of a rectangle is " + (2 * (getW() + getH()));
        return rec;
    }

}
