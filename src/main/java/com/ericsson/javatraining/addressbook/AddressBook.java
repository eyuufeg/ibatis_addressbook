package com.ericsson.javatraining.addressbook;

import java.util.LinkedList;
import java.util.List;

public class AddressBook {
    private List<UserInfo> allUsers;

    public AddressBook() {
        super();
        allUsers = new LinkedList<UserInfo>();
    }

    public List<UserInfo> getallUsers() {
        return allUsers;
    }

    public void setAllUsers(List<UserInfo> allUsers) {
        this.allUsers = allUsers;
    }

    public boolean adduser(UserInfo user) {
        if (allUsers.contains(user)) {
            return false;
        } else {
            allUsers.add(user);
            return true;
        }
    }

    // TODO maybe has problems.
    public boolean deleteUser(String name) {
        for (UserInfo user : allUsers) {
            if (user.getName().equalsIgnoreCase(name)) {
                allUsers.remove(user);
                return true;
            }
        }
        return false;
    }

    public List<UserInfo> listallUsers() {
        return allUsers;

    }

    public List<UserInfo> searchUser(String phone) {
        List<UserInfo> searchlist = new LinkedList<UserInfo>();
        for (UserInfo user : allUsers) {
            if (user.getPhone().contains(phone)) {
                searchlist.add(user);
            }
        }
        return searchlist;

    }
}
