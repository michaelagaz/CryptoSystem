package com.company;

/**
 * Created by Michaela on 19.10.2017.
 */
public class Rule {
    private String label;
    private String leftSide;
    private String rightSide;

    public Rule(String label, String leftSide, String rightSide) {
        this.label = label;
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(String leftSide) {
        this.leftSide = leftSide;
    }

    public String getRightSide() {
        return rightSide;
    }

    public void setRightSide(String rightSide) {
        this.rightSide = rightSide;
    }
}
