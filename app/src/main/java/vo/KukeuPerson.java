package vo;

import io.realm.RealmModel;
import lombok.Data;
import rx.Observable;

/**
 * Created by hyunji on 16. 7. 10..
 */
@Data
public class KukeuPerson implements RealmModel {

    public String name;
    public String job;
    public String gender;

    public static Observable<KukeuPerson> asObservable() {

        return null;
    }
}
