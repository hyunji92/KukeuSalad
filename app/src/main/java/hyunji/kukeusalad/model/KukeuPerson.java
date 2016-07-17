package hyunji.kukeusalad.model;

import io.realm.RealmObject;
import lombok.Data;

/**
 * Created by hyunji on 16. 7. 10..
 */
@Data
public class KukeuPerson extends RealmObject {

    public String name;
    public String job;
    public String gender;


    //public Observable<KukeuPerson> asObservable() {

    //    return null;
    //}
}
