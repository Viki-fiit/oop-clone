package com.example.starhike;
import Hike.BaseHike;
import User.BaseUser;
import Other.GlobalVariables;

public class Initialize {
    public Initialize(){
        BaseUser user1 = new BaseUser("viki", "asdf");
        user1.setLvl(1);
        user1.setPreffType(3);
        GlobalVariables.allUsers.add(user1);

        BaseUser user2 = new BaseUser("luki", "qwer");
        user1.setLvl(0);
        user1.setPreffType(1);
        GlobalVariables.allUsers.add(user2);

        BaseUser user3 = new BaseUser("hana", "zxcv");
        user1.setLvl(0);
        user1.setPreffType(4);
        GlobalVariables.allUsers.add(user3);

        BaseUser user4 = new BaseUser("admin", "admin");
        user1.setLvl(4);
        user1.setPreffType(4);
        GlobalVariables.allUsers.add(user4);



        GlobalVariables.allHikes.add(BaseHike.newHike().NAME("krivan").MIN(320).Type(1).Difficulty(0).AVL(true).WeatherOK(1).build());
        GlobalVariables.allHikes.add(BaseHike.newHike().NAME("Zubac").MIN(200).Type(0).Difficulty(1).AVL(true).WeatherOK(2).build());


    }
}
