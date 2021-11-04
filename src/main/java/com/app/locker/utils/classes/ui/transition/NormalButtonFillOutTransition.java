package com.app.locker.utils.classes.ui.transition;

import javafx.animation.FillTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class NormalButtonFillOutTransition {

    private final FillTransition buttonFillOutTransition;

    public NormalButtonFillOutTransition(Shape shape){
        buttonFillOutTransition = new FillTransition(Duration.millis(200));
        buttonFillOutTransition.setFromValue(Color.rgb(224, 11, 11));
        buttonFillOutTransition.setToValue(Color.rgb(125, 125, 125));
        buttonFillOutTransition.setCycleCount(1);
        buttonFillOutTransition.setShape(shape);
    }

    public FillTransition getButtonFillOutTransition(){
        return buttonFillOutTransition;
    }

}
