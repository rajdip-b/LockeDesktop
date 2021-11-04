package com.app.locker.utils.classes.ui.animation;

import com.app.locker.utils.classes.core.ObjectHolder;
import com.app.locker.utils.classes.ui.transition.ImageButtonFillInTransition;
import com.app.locker.utils.classes.ui.transition.ImageButtonFillOutTransition;
import com.app.locker.utils.classes.ui.transition.NormalButtonFillInTransition;
import com.app.locker.utils.classes.ui.transition.NormalButtonFillOutTransition;
import javafx.animation.FillTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ButtonHoverAnimation {

    private final Button button;

    // For image buttons
    public ButtonHoverAnimation(Button button, Image image){
        this.button = button;
        ImageView imageViewWhite = new ImageView(ObjectHolder.getImageCache().getAlternateImage(image));
        imageViewWhite.setFitHeight(34);
        imageViewWhite.setFitWidth(36);
        ImageView imageViewColoured = new ImageView(image);
        imageViewColoured.setFitWidth(36);
        imageViewColoured.setFitHeight(34);
        Rectangle rectangleButton = new Rectangle();
        rectangleButton.setWidth(54);
        rectangleButton.setHeight(56);
        rectangleButton.setArcHeight(10);
        rectangleButton.setArcWidth(10);
        button.setGraphic(imageViewColoured);
        button.setShape(rectangleButton);
        button.setStyle("-fx-background-color: white");
        button.getShape().fillProperty().addListener((observable, oldPaint, newPaint) -> {
            String style=String.format("-fx-background-color: #%s", newPaint.toString().substring(2));
            button.setStyle(style);
        });
        button.setOnMouseEntered(event -> {
            new ImageButtonFillInTransition(button.getShape()).getButtonFillInTransition().play();
            button.setGraphic(imageViewWhite);
        });
        button.setOnMouseExited(event -> {
            new ImageButtonFillOutTransition(button.getShape()).getButtonFillOutTransition().play();
            button.setGraphic(imageViewColoured);
        });
    }

    // For normal buttons
    public ButtonHoverAnimation(Button button){
        this.button = button;
        Rectangle rectangleButton = new Rectangle();
        rectangleButton.setWidth(54);
        rectangleButton.setHeight(56);
        rectangleButton.setArcHeight(5);
        rectangleButton.setArcWidth(5);
        button.setShape(rectangleButton);
        button.setStyle("-fx-background-color: rgb(125, 125, 125)");
        button.getShape().fillProperty().addListener((observable, oldPaint, newPaint) -> {
            String style=String.format("-fx-background-color: #%s", newPaint.toString().substring(2));
            button.setStyle(style);
        });
        button.setOnMouseEntered(event -> {
            new NormalButtonFillInTransition(button.getShape()).getButtonFillInTransition().play();
        });
        button.setOnMouseExited(event -> {
            new NormalButtonFillOutTransition(button.getShape()).getButtonFillOutTransition().play();
        });
    }

}
