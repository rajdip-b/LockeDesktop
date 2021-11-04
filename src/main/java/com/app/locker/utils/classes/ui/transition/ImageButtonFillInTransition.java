package com.app.locker.utils.classes.ui.transition;

import javafx.animation.FillTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class ImageButtonFillInTransition {

    private final FillTransition buttonFillInTransition;

    public ImageButtonFillInTransition(Shape shape){
        buttonFillInTransition = new FillTransition(Duration.millis(200));
        buttonFillInTransition.setFromValue(Color.WHITE);
        buttonFillInTransition.setToValue(Color.rgb(125, 125, 125));
        buttonFillInTransition.setCycleCount(1);
        buttonFillInTransition.setShape(shape);
    }

    public FillTransition getButtonFillInTransition(){
        return buttonFillInTransition;
    }

}
