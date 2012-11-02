package com.ericsson.javatraining.userinterface;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.ericsson.javatraining.addressbook.AddressBook;
import com.ericsson.javatraining.addressbook.UserInfo;
import com.ericsson.javatraining.datalayer.DataProcess;


/**
 * process input commands
 * 
 * @author eyuufeg
 * 
 */
public class CommandProcess implements CommandProcessInterface {
    private AddressBook addressBook;
    private DataProcess dataProc;
    private List<UserInfo> addlist;
    private List<String> deletelist;

    public CommandProcess() {
        addressBook = new AddressBook();
        dataProc = new DataProcess();
        addressBook.setAllUsers(dataProc.readFromDatabase());
        addlist = new LinkedList<UserInfo>();
        deletelist = new LinkedList<String>();
    }

    public void process() {
        System.out.println("************************************************************************");
        System.out.println("                         AddressBook                                    ");
        System.out.println("                  *add:add new User Address   ");
        System.out.println("                  *delete:delete User            ");
        System.out.println("                  *search:search User by nuber");
        System.out.println("                  *list:list all User          ");
        System.out.println("                  *quit:quit the program       ");
        System.out.println("************************************************************************");

        while (true) {

            System.out.print("please input the option(add/delete/search/list/quit):");

            String readLine = getCommand();

            String command = readLine.toLowerCase();
            if (command.equals("add"))
                add();                       
            else if (command.equals("delete"))
                delete();           
            else if (command.equals("search"))
                search();
            else if (command.equals("list"))
                list();
            else if (command.equals("quit")) {
                dataProc.writeToDatabase(addlist, deletelist);
                quit();
                break;
            }
            else
                System.out.print("input error,please input again");
        }

    
    }

    public String getCommand() {
        return new Scanner(System.in).nextLine().trim();
    }

    public void add() {
        UserInfo userInfo = new UserInfo();
        //read name from input 
        System.out.print("please input the name:");
        String readname = getCommand();
        userInfo.setName(readname);
        //read phone from input
        System.out.print("please input the number:");
        String readphone = getCommand();
        userInfo.setPhone(readphone);
        //read address from input
        System.out.print("please input the address:");
        String readaddress = getCommand();
        userInfo.setAddress(readaddress);
        // System.out.print(user);
        addlist.add(userInfo);
        boolean addResult = addressBook.adduser(userInfo);
        if (addResult) {
            System.out.println("add new user " + readname + " successfully");

        } else {
            System.out.println("add new user " + readname + " failed");
        }

    }

    public void delete() {
        // read name will be delete from input
        System.out.print("please input the name will be delete:");
        String readname = getCommand();

        deletelist.add(readname);

        if (addressBook.deleteUser(readname)) {
            System.out.println("delete user " + readname + " successfully");
        } else {
            System.out.println("delete user " + readname + " failed");
        }

    }

    public void search() {
        List<UserInfo> searchlist = new LinkedList<UserInfo>();
        // read phone from input
        System.out.print("please input the phone:");
        String readphone = getCommand();
        searchlist = addressBook.searchUser(readphone);
        for (UserInfo userInfo : searchlist) {
            System.out.println(userInfo);
        }
    }

    public void list() {
        List<UserInfo> list = new LinkedList<UserInfo>();
        list = addressBook.listallUsers();
        for (UserInfo userInfo : list) {
            System.out.println(userInfo);
        }
    }

    private void quit() {
        System.out.println("quit the address book application");
        return;
    }

}
