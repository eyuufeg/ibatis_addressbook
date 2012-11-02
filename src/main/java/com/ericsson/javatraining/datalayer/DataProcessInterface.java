package com.ericsson.javatraining.datalayer;

import java.util.List;

import com.ericsson.javatraining.addressbook.UserInfo;

/**
 * .
 * 
 * @author eyuufeg
 */
public interface DataProcessInterface {

    List<UserInfo> readFromDatabase();

    void writeToDatabase(List<UserInfo> addlist, List<String> deletelist);

}
