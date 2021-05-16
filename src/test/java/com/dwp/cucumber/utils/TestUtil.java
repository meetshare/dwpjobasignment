package com.dwp.cucumber.utils;

import com.dwp.cucumber.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.spatial.util.GeoDistanceUtils;

import static com.spatial4j.core.distance.DistanceUtils.KM_TO_MILES;

/*

    This class has helper function to parse the response received from endpoints
    And also, the function to verify the users within miles of a city
 */
public class TestUtil {


    public final static double LONDON_LAT=51.509865;
    public final static double LONDON_LON=-0.118092;

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

    public static double getDistanceFromLondon(double lat,double lon){
        double distance = GeoDistanceUtils.haversin(lat, lon, LONDON_LAT, LONDON_LON);

        return distance * KM_TO_MILES;
    }
}
