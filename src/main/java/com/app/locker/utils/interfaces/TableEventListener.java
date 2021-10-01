package com.app.locker.utils.interfaces;

import com.app.locker.model.Entry;

public interface TableEventListener {

    public void onItemAdded(Entry entry);
    public void onItemEdited(Entry entry);
    public void onItemDeleted(Entry entry);

}
