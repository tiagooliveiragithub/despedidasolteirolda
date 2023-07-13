package com.tiago.despedidasolteirolda.entities;

public class Permissions {

    private boolean createUsers;
    private boolean editUsers;
    private boolean deleteUsers;

    public Permissions() {
    }

    public boolean isCreateUsers() {
        return createUsers;
    }

    public void setCreateUsers(boolean createUsers) {
        this.createUsers = createUsers;
    }

    public boolean isEditUsers() {
        return editUsers;
    }

    public void setEditUsers(boolean editUsers) {
        this.editUsers = editUsers;
    }

    public boolean isDeleteUsers() {
        return deleteUsers;
    }

    public void setDeleteUsers(boolean deleteUsers) {
        this.deleteUsers = deleteUsers;
    }
}
