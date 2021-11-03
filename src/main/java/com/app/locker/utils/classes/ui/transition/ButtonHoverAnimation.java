package com.app.locker.utils.classes.ui.transition;

import com.app.locker.utils.classes.core.ObjectHolder;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class ButtonHoverAnimation {

    private final Button button;

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
            new ButtonFillInTransition(button.getShape()).getButtonFillInTransition().play();
            button.setGraphic(imageViewWhite);
        });
        button.setOnMouseExited(event -> {
            new ButtonFillOutTransition(button.getShape()).getButtonFillOutTransition().play();
            button.setGraphic(imageViewColoured);
        });
    }

}
