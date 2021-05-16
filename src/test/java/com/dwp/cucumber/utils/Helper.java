package com.dwp.cucumber.utils;

import com.dwp.cucumber.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*
    This class has helper function to parse the response received from endpoints
    And also, the function to verify the users within miles of a city
 */
public class Helper {
    public static List<User> getAllUsers(String response){
        List<User> allUsers = new ArrayList<User>();
        ObjectMapper mapper = new ObjectMapper();
        JSONArray array = new JSONArray(response);
        for(int i=0; i < array.length(); i++)
        {
            JSONObject sss = array.getJSONObject(i);
            try{
                User user = mapper.readValue(sss.toString(), User.class);
                allUsers.add(user);
            }catch (IOException e){
                System.out.println("Error : " +e.toString());
            }
        }
        return allUsers;
    }
}
