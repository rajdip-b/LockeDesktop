package com.app.locker.utils.interfaces;

import com.app.locker.controller.fragments.EntryViewController;
import com.app.locker.model.Entry;

public interface EntryEventListener extends EventListener{

    void onEntrySelected(EntryViewController entryViewController);
    void onEntryDeselected(EntryViewController entryViewController);

}
