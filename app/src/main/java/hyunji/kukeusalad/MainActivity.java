package hyunji.kukeusalad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import adapter.PersonListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import vo.KukeuPerson;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.profile_img)
    ImageView vProfileImg;

    @BindView(R.id.toolbar)
    Toolbar vToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView vRecyclerView;

    private List<KukeuPerson> kukeuPersonList;
    private PersonListAdapter personListAdapter;

    Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        RealmResults<KukeuPerson> persons = realm.where(KukeuPerson.class).findAll();
        KukeuPerson person = persons.first();

        Observable<Realm> realmObservable = realm.asObservable();
        Observable<RealmResults<KukeuPerson>> resultsObservable = persons.asObservable();
        // ???  Observable<KukeuPerson> objectObservable = KukeuPerson.asObservable();

        kukeuPersonList = new ArrayList<>();

        for (int i=0; i<20 ; i++){

            kukeuPersonList.add(new KukeuPerson());
        }

        personListAdapter = new PersonListAdapter(kukeuPersonList);
        vRecyclerView.setAdapter(personListAdapter);
        vRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }

    public void realmTest(){

        realm.where(KukeuPerson.class).equalTo("name", "John").findAllAsync().asObservable()
                .filter(new Func1<RealmResults<KukeuPerson>, Boolean>() {
                    @Override
                    public Boolean call(RealmResults<KukeuPerson> persons) {
                        // Ignore unloaded results
                        return persons.isLoaded();
                    }
                })
                .subscribe(new Action1<RealmResults<KukeuPerson>>() {
                    @Override
                    public void call(RealmResults<KukeuPerson> persons) {
                        // Show persons...
                    }
                });
    }

}
