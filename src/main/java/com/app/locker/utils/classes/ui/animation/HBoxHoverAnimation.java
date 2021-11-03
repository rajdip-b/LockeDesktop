package com.app.locker.utils.classes.ui.animation;

import com.app.locker.utils.classes.core.ObjectHolder;
import com.app.locker.utils.classes.ui.transition.HBoxFillInTransition;
import com.app.locker.utils.classes.ui.transition.HBoxFillOutTransition;
import com.app.locker.utils.classes.ui.transition.LabelFillInTransition;
import com.app.locker.utils.classes.ui.transition.LabelFillOutTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class HBoxHoverAnimation {

    private final HBox hBox;
    private final Label label;
    private final ImageView imageView;
    private final Rectangle rectangleLabel;
    private final Rectangle rectangleHBox;

    public HBoxHoverAnimation(HBox hBox) {
        this.hBox = hBox;
        imageView = (ImageView) hBox.getChildren().get(0);
        label = (Label) hBox.getChildren().get(1);
        rectangleHBox = new Rectangle();
        rectangleHBox.setWidth(220);
        rectangleHBox.setHeight(56);
        rectangleHBox.setArcHeight(5);
        rectangleHBox.setArcWidth(5);

        rectangleLabel = new Rectangle();
        rectangleLabel.setWidth(183);
        rectangleLabel.setHeight(59);
        rectangleLabel.setArcHeight(5);
        rectangleLabel.setArcWidth(5);

        hBox.setShape(rectangleHBox);
        label.setShape(rectangleLabel);
        hBox.getShape().fillProperty().addListener((observable, oldValue, newValue) -> {
            String style=String.format("-fx-background-color: #%s", newValue.toString().substring(2));
            hBox.setStyle(style);
        });
        label.getShape().fillProperty().addListener((observable, oldValue, newValue) -> {
            String style=String.format("-fx-text-fill: #%s", newValue.toString().substring(2));
            label.setStyle(style);
        });
        hBox.setOnMouseExited(event -> {
            new LabelFillOutTransition(label.getShape()).getLabelFillOutTransition().play();
            new HBoxFillOutTransition(hBox.getShape()).getHBoxFillOutTransition().play();
            imageView.setImage(ObjectHolder.getImageCache().getAlternateImage(imageView.getImage()));
        });
        hBox.setOnMouseEntered(event -> {
            new LabelFillInTransition(label.getShape()).getLabelFillInTransition().play();
            new HBoxFillInTransition(hBox.getShape()).getHBoxFillInTransition().play();
            imageView.setImage(ObjectHolder.getImageCache().getAlternateImage(imageView.getImage()));
        });
    }
}
