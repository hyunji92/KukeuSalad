package hyunji.kukeusalad.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

/**
 * Created by hyunji on 16. 7. 10..
 */
@Data
public class KukeuPerson extends RealmObject {

    @PrimaryKey
    private long id;
    public String name;
    public String job;
    public String gender;

}
