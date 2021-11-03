package com.app.locker.utils.classes.ui.transition;

import javafx.animation.FillTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class HBoxFillInTransition {

    private final FillTransition hBoxFillInTransition;

    public HBoxFillInTransition(Shape shape){
        hBoxFillInTransition = new FillTransition(Duration.millis(200));
        hBoxFillInTransition.setFromValue(Color.WHITE);
        hBoxFillInTransition.setToValue(Color.rgb(224, 11, 11));
        hBoxFillInTransition.setCycleCount(1);
        hBoxFillInTransition.setShape(shape);
    }

    public FillTransition getHBoxFillInTransition(){
        return hBoxFillInTransition;
    }

}
