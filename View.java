package mvc22f;

import javax.swing.*;
import java.awt.*;

public final class View extends JPanel {
    private final Model model;

    private int xCenter;
    private int yCenter;
    private int xStart;
    private int yStart;
    private int width;
    private int height;

    public View(Model model) {
        this.model = model;
        System.out.println("View c-tor: model = " + model);
        setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);  // call the super class c-tor
        setUp();

        // update drawing color
        g.setColor(model.getColor());

        // clear out the old picture
        g.clearRect(0,0, getWidth(), getHeight());

        // draw new picture
        switch(model.getTask()) {
            case ELLIPSE:
                paintEllipse(g);
                break;
            case RECTANGLE:
                paintRectangle(g);
                break;
            case INFORMATION:
                paintStudentInfo(g);
                break;
            default:
                throw new RuntimeException("Bummer! Reached default case "
                        + "in View.paintComponent switch");
        }
    }

    private void setUp() {
        xCenter = getWidth() / 2;
        yCenter = getHeight() / 2;
        xStart = xCenter - model.getxSize();
        yStart = yCenter - model.getySize();
        width = 2 * model.getxSize();
        height = 2 * model.getySize();
    }

    private void paintEllipse(Graphics g) {
        if (model.isSolid()) {
            g.fillOval(xStart, yStart, width, height); 
        } else {
            g.drawOval(xStart, yStart, width, height);
        }
    }

    private void paintRectangle(Graphics g) {
        if (model.isSolid()) {
            g.fillRect(xStart, yStart, width, height);
        } else {
            g.drawRect(xStart, yStart, width, height);
        }
    }

    private final String studentTitle = "Programming Wizard"; // pick favorite title
    private final String studentName = "Allison McKee"; // use actual name
    private final String studentAffiliation = "Ohio University";
    private final int MIN_LENGTH = 4;
    private final double FONT_FACTOR = 0.3;
    private final int INFO_X_START = 100;
    private final double Y_FRACTION_1 = 0.3;
    private final double Y_FRACTION_2 = 0.5;
    private final double Y_FRACTION_3 = 0.7;

    private void paintStudentInfo(Graphics g) {
        int maxStringLength = Math.max(studentTitle.length(),
                studentName.length());
            maxStringLength = Math.max(maxStringLength,
                    studentAffiliation.length());
            maxStringLength = Math.min(maxStringLength, MIN_LENGTH);
            int fontSize = (int) (FONT_FACTOR * getWidth() / maxStringLength);
            g.setFont(new Font("Courier", Font.PLAIN, fontSize));
            g.setColor(model.getColor());
            g.drawString(studentTitle, INFO_X_START,
                    (int) (Y_FRACTION_1 * getHeight()));
            g.drawString(studentName, INFO_X_START,
                    (int) (Y_FRACTION_2 * getHeight()));
            g.drawString(studentAffiliation, INFO_X_START,
                    (int) (Y_FRACTION_3 * getHeight()));
    }
}
