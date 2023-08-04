package com.naver.phonebook.service;

import java.util.ArrayList;

import com.naver.phonebook.dao.PhoneBookDao;
import com.naver.phonebook.sdto.PhoneBookSDto;

public class phonebookservice {
   public int equalcheck(String email, String userpassword) throws Exception {
      PhoneBookDao phonebookdao = new PhoneBookDao();
      ArrayList<PhoneBookSDto> usertable = phonebookdao.loginfindAll();
      int check = 1;
      for (int i = 0; i < usertable.size(); i++) {
         if(email.equals(usertable.get(i).getEmail())
               && userpassword.equals(usertable.get(i).getPw())) {
            check = 1;
            break;
         }else {
            check = 0;
         }
         
      }
      return check;
   }
}