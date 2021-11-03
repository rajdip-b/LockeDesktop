package com.app.locker.utils.classes.ui.transition;

import javafx.animation.FillTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class ButtonFillOutTransition {

    private final FillTransition buttonFillOutTransition;

    public ButtonFillOutTransition(Shape shape){
        buttonFillOutTransition = new FillTransition(Duration.millis(200));
        buttonFillOutTransition.setFromValue(Color.rgb(125, 125, 125));
        buttonFillOutTransition.setToValue(Color.WHITE);
        buttonFillOutTransition.setCycleCount(1);
        buttonFillOutTransition.setShape(shape);
    }

    public FillTransition getButtonFillOutTransition(){
        return buttonFillOutTransition;
    }

}
