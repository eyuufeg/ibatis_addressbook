package com.ericsson.javatraining.datalayer;

import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import com.ericsson.javatraining.addressbook.UserInfo;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;


/**
 * 
 * 
 * @author eyuufeg
 */
public class DataProcess implements DataProcessInterface {

    private UserInfo user;
    private String resource = "sqlmap/SqlMapConfig.xml";
    private SqlMapClient sqlMap;

    public DataProcess() {

        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void addList(List<UserInfo> addist) {
        try {
            for (int i = 0; i < addist.size(); i++) {
                user = addist.get(i);
                sqlMap.insert("insert_address_book", user);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void deleteList(List<String> deletelist) {
        String name;
        try {
            for (int i = 0; i < deletelist.size(); i++) {
                name = deletelist.get(i);
                sqlMap.delete("delete_address_book", name);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<UserInfo> readFromDatabase() {
        List<UserInfo> userlist = null;
        try {
            userlist = sqlMap.queryForList("getAllUsers", null);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (UserInfo userInfo : userlist) {
            System.out.println(userInfo);
        }
        return userlist;
    }

    public void writeToDatabase(List<UserInfo> addlist, List<String> deletelist) {
        for (UserInfo userInfo : addlist) {
            System.out.println(userInfo);
        }
        for (String name : deletelist) {
            System.out.println(name);
        }
        addList(addlist);
        deleteList(deletelist);
    }


}
