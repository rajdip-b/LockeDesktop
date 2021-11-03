package com.app.locker.utils.classes.ui.animation;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class EntryViewAnimation {

    public EntryViewAnimation(Node entryView){
        DropShadow dropShadowGrey = new DropShadow(5, Color.GREY);
        DropShadow dropShadowRed = new DropShadow(5, Color.RED);
        entryView.setEffect(dropShadowGrey);
        entryView.setOnMouseEntered(event -> entryView.setEffect(dropShadowRed));
        entryView.setOnMouseExited(event -> entryView.setEffect(dropShadowGrey));
    }

}
