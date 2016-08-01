package hyunji.kukeusalad.util;

import com.firebase.client.Firebase;

import java.util.HashMap;

/**
 * Created by hyunji on 2016. 7. 30..
 */

public class DataService {


    final static String BASE_URL = "https://<KukeuSalad>.firebaseio.com";

    final static Firebase _BASE_REF = new Firebase(BASE_URL);
    final static Firebase _USER_REF = new Firebase(BASE_URL + "/users/");
    final static Firebase _JOKE_REF = new Firebase(BASE_URL + "/jokes/");

    // private static 로 선언.
    private static DataService instance = new DataService();
    //private static MyPreferenceManager pref = MyApplication.getInstance().getPrefManager();

    // 조회 method
    private static class Singleton {
        private static final DataService instance = new DataService();
    }

    public static DataService getInstance () {
        System.out.println("create instance");
        return Singleton.instance;
    }


    public static Firebase CURRENT_USER_REF() {
        String userID = pref.getValue(MyPreferenceManager.UID,"");
        //Firebase currentUserRef = new Firebase(DataService._USER_REF + "/" + pref.getValue(MyPreferenceManager.UID,""));
        return _BASE_REF.child("users").child(userID);

    }

    public static void createNewJoke(HashMap<String, Object> joke) {
        Firebase firebaseNewJoke = _JOKE_REF.push();
        firebaseNewJoke.setValue(joke);

    }

}
