package com.app.locker.utils.classes.ui.transition;

import javafx.animation.FillTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class HBoxFillOutTransition {

    private final FillTransition hBoxFillOutTransition;

    public HBoxFillOutTransition(Shape shape){
        hBoxFillOutTransition = new FillTransition(Duration.millis(200));
        hBoxFillOutTransition.setToValue(Color.WHITE);
        hBoxFillOutTransition.setFromValue(Color.rgb(224, 11, 11));
        hBoxFillOutTransition.setCycleCount(1);
        hBoxFillOutTransition.setShape(shape);
    }

    public FillTransition getHBoxFillOutTransition(){
        return hBoxFillOutTransition;
    }

}
