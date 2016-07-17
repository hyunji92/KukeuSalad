package hyunji.kukeusalad.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import hyunji.kukeusalad.R;
import hyunji.kukeusalad.adapter.PersonListAdapter;
import hyunji.kukeusalad.model.KukeuPerson;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by hyunji on 16. 7. 16..
 */
public class MainFragment extends Fragment {
    private  final  String[] nameList = {"","정현지","이혜정","정고은","이윤정","이예진","진유림","김나연","정지윤","진아","백설아","순자","미자","혜자","영자","은자","","최현묵","하동현","이강산","최현묵","하동현","이강산","최현묵","하동현","이강산","최현묵","하동현","이강산","최현묵","하동현","이강산","하동현","이강산"};
    private  final  String[] jobList = {"","개발자","개발자","회사원","공무원","경영","개발자","디자이너","학생","디자이너","공연예술","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자","개발자"};
    private  final  String[] genderList = {"girl","girl","girl","girl","girl","girl","girl","girl","girl","girl","girl","girl","girl","girl","girl","boy","boy","boy","boy","boy","boy","boy","boy","boy","boy","boy","boy","boy","boy","boy","boy","boy","boy"};



    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolBar;

    private Context context;
    private Realm realm;
    private PersonListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this, view);
        context = getContext();
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolBar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PersonListAdapter(context);
        recyclerView.setAdapter(adapter);

        //RealmConfiguration mRealmConfig = new RealmConfiguration.Builder(context).build();
        //Realm.setDefaultConfiguration(mRealmConfig);

        realm = Realm.getDefaultInstance();

        Observable<RealmResults<KukeuPerson>> realmResults = realm.where(KukeuPerson.class).findAllAsync().asObservable();
        realmResults
                .filter(RealmResults::isLoaded)
                .subscribe(realmResults1 -> {
                    if(realmResults1.isEmpty()){
                        dummy();
                    }
                    Log.d("TEST", "onCreate: " + realmResults1.size());
                    Log.v("DATA", "onCreate: " + realmResults1.toString());
                    adapter.setData(realmResults1);

                });

        return view;
    }

    public void dummy(){
        // data 들어간 작업
        realm.beginTransaction();
        for (int i=0; i<nameList.length ; i++) {
            KukeuPerson person = realm.createObject(KukeuPerson.class);
            person.setName(nameList[i]);
            person.setJob(jobList[i]);
            person.setGender(genderList[i]);

        }
        realm.commitTransaction();
    }
}
