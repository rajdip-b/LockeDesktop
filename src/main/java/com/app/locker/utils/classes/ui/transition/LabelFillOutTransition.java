package com.app.locker.utils.classes.ui.transition;

import javafx.animation.FillTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class LabelFillOutTransition {

    private final FillTransition labelFillOutTransition;

    public LabelFillOutTransition(Shape shape){
        labelFillOutTransition = new FillTransition(Duration.millis(200));
        labelFillOutTransition.setFromValue(Color.WHITE);
        labelFillOutTransition.setToValue(Color.BLACK);
        labelFillOutTransition.setCycleCount(1);
        labelFillOutTransition.setShape(shape);
    }

    public FillTransition getLabelFillOutTransition(){
        return labelFillOutTransition;
    }

}
