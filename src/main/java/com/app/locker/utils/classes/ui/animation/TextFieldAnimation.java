package com.app.locker.utils.classes.ui.animation;

import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TextFieldAnimation {

    public TextFieldAnimation(TextField textField){
        Border borderGrey = new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, new CornerRadii(0.4), new BorderWidths(0,0,2,0)));
        Border borderRed = new Border(new BorderStroke(Color.rgb(224, 11, 11), BorderStrokeStyle.SOLID, new CornerRadii(0.4), new BorderWidths(0,0,2,0)));
        DropShadow dropShadow = new DropShadow(5, Color.GREY);
        textField.setStyle("-fx-background-color: -fx-control-inner-background;");
        textField.setBorder(borderGrey);
        textField.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue){
                textField.setBorder(borderRed);
                textField.setEffect(dropShadow);
            }else{
                textField.setBorder(borderGrey);
                textField.setEffect(null);
            }
        }));
    }

}
