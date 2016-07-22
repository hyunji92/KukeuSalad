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
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicLong;

import butterknife.BindView;
import butterknife.ButterKnife;
import hyunji.kukeusalad.R;
import hyunji.kukeusalad.adapter.PersonListAdapter;
import hyunji.kukeusalad.model.KukeuPerson;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;

/**
 * Created by hyunji
 */
public class MainFragment extends Fragment implements MainView{
    private final String[] nameList = {"정현지", "최현묵", "정고은", "이윤정", "이예진", "진유림", "김나연", "정지윤", "진아", "백설아", "순자", "미자", "혜자", "영자", "은자", "최현묵", "하동현", "이강산", "최현묵", "하동현", "이강산", "최현묵", "하동현", "이강산", "최현묵", "하동현", "이강산", "최현묵", "하동현", "이강산2"};
    private final String[] jobList = {"개발자", "개발자", "회사원", "공무원", "경영", "개발자", "디자이너", "학생", "디자이너", "공연예술", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자"};
    private final String[] genderList = {"girl", "girl", "boy", "girl", "girl", "girl", "girl", "girl", "girl", "girl", "girl", "girl", "girl", "girl", "girl", "girl", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy"};


    @BindView(R.id.content_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolBar;

    private Realm realm;
    private PersonListAdapter adapter;

    private Context context = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolBar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new PersonListAdapter(context);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((position, v) -> Log.d("TEST", "onItemClick position: " + position));

        realm = Realm.getDefaultInstance();

        RealmResults<KukeuPerson> realmResults = realm.where(KukeuPerson.class).findAll();

        if (realmResults.isEmpty()) {
            dummy();
        }

        Observable<RealmResults<KukeuPerson>> results = realm.where(KukeuPerson.class)
                .findAllAsync()
                .sort("id")
                .sort("gender", Sort.DESCENDING)
                .asObservable();
        results
                .filter(RealmResults::isLoaded)
                .subscribe(results1 -> {
                    Log.d("TEST", "onCreate: " + results1.size());
                    adapter.setData(results1);
                });

        return view;
    }

    public void dummy() {
        AtomicLong al = new AtomicLong(1);

        // data 들어간 작업
        realm.executeTransaction(realm1 -> {
            for (int i = 0; i < nameList.length; i++) {

                KukeuPerson person = realm1.createObject(KukeuPerson.class);
                person.setName(nameList[i % nameList.length]);
                person.setJob(jobList[i % jobList.length]);
                person.setGender(genderList[i % genderList.length]);
                person.setId(al.getAndIncrement());
            }
        });
    }

    @Override
    public void TextToast(String toast) {

        Toast.makeText(getContext(),toast,Toast.LENGTH_LONG).show();
    }
}
