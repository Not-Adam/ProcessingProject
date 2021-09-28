package me.adam.processing.slider;

import me.adam.processing.MainClass;

public class Scrollbar extends MainClass {
    private int swidth, sheight;    // width and height of bar
    private float xpos, ypos;       // x and y position of bar
    private float spos, newspos;    // x position of slider
    private float sposMin, sposMax; // max and min values of slider
    private int loose;              // how loose/heavy
    private boolean over;           // is the mouse over the slider?
    private boolean locked;
    private float ratio;

    private Scrollbar(float xp, float yp, int sw, int sh, int l) {
        swidth = sw;
        sheight = sh;
        int widthtoheight = sw - sh;
        ratio = (float)sw / (float)widthtoheight;
        xpos = xp;
        ypos = yp-sheight/2;
        spos = xpos + swidth/2 - sheight/2;
        newspos = spos;
        sposMin = xpos;
        sposMax = xpos + swidth - sheight;
        loose = l;
    }

    public void update() {
        if (overEvent()) {
            over = true;
        } else {
            over = false;
        }
        if (mousePressed && over) {
            locked = true;
        }
        if (!mousePressed) {
            locked = false;
        }
        if (locked) {
            newspos = constraint(mouseX-sheight/2, sposMin, sposMax);
        }
        if (abs(newspos - spos) > 1) {
            spos = spos + (newspos-spos)/loose;
        }
    }

    public float constraint(float val, float minv, float maxv) {
        return min(max(val, minv), maxv);
    }

    public boolean overEvent() {
        if (mouseX > xpos && mouseX < xpos+swidth &&
                mouseY > ypos && mouseY < ypos+sheight) {
            return true;
        } else {
            return false;
        }
    }

    public void display() {
        noStroke();
        fill(204);
        rect(xpos, ypos, swidth, sheight);
        if (over || locked) {
            fill(0, 0, 0);
        } else {
            fill(102, 102, 102);
        }
        rect(spos, ypos, sheight, sheight);
    }

    public float getPos() {
        // Convert spos to be values between
        // 0 and the total width of the scrollbar
        return spos * ratio;
    }
}