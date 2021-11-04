package com.app.locker.utils.classes.ui.transition;

import javafx.animation.FillTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class NormalButtonFillInTransition {

    private final FillTransition buttonFillInTransition;

    public NormalButtonFillInTransition(Shape shape){
        buttonFillInTransition = new FillTransition(Duration.millis(200));
        buttonFillInTransition.setToValue(Color.rgb(224, 11, 11));
        buttonFillInTransition.setFromValue(Color.rgb(125, 125, 125));
        buttonFillInTransition.setCycleCount(1);
        buttonFillInTransition.setShape(shape);
    }

    public FillTransition getButtonFillInTransition(){
        return buttonFillInTransition;
    }

}
