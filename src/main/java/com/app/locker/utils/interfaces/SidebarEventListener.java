package com.app.locker.utils.interfaces;

public interface SidebarEventListener extends EventListener{

    void onCollapseSidebar();
    void onExpandSidebar();
    void onAddClicked();
    void onEditClicked();
    void onDeleteClicked();
    void onDeleteAllClicked();
    void onGithubClicked();

}
