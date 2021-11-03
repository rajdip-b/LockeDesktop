package com.app.locker.utils.classes.ui.transition;

import javafx.animation.FillTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class LabelFillInTransition {

    private final FillTransition labelFillInTransition;

    public LabelFillInTransition(Shape shape){
        labelFillInTransition = new FillTransition(Duration.millis(200));
        labelFillInTransition.setFromValue(Color.BLACK);
        labelFillInTransition.setToValue(Color.WHITE);
        labelFillInTransition.setCycleCount(1);
        labelFillInTransition.setShape(shape);
    }

    public FillTransition getLabelFillInTransition(){
        return labelFillInTransition;
    }

}
