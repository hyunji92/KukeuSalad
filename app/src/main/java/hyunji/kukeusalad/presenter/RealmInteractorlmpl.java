package hyunji.kukeusalad.presenter;

import android.util.Log;

import java.util.concurrent.atomic.AtomicLong;

import hyunji.kukeusalad.model.KukeuPerson;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;

/**
 * Created by hyunji on 16. 7. 23..
 */
public class RealmInteractorlmpl implements RealmInteractor {

    private final String[] nameList = {"정현지", "최현묵", "정고은", "이윤정", "이예진", "진유림", "김나연", "정지윤", "진아", "백설아", "순자", "미자", "혜자", "영자", "은자", "최현묵", "하동현", "이강산", "최현묵", "하동현", "이강산", "최현묵", "하동현", "이강산", "최현묵", "하동현", "이강산", "최현묵", "하동현", "이강산2"};
    private final String[] jobList = {"개발자", "개발자", "회사원", "공무원", "경영", "개발자", "디자이너", "학생", "디자이너", "공연예술", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자", "개발자"};
    private final String[] genderList = {"girl", "girl", "boy", "girl", "girl", "girl", "girl", "girl", "girl", "girl", "girl", "girl", "girl", "girl", "girl", "girl", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy", "boy"};

    private Realm realm;

    public RealmInteractorlmpl() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void initRealmData() {

        // Realm 에 대한 presenter 다시빼서 작업
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
    public void realmDataLoad(OnFinishedListener listener) {
        Observable<RealmResults<KukeuPerson>> results = realm.where(KukeuPerson.class)
                .findAllAsync()
                .sort("id")
                .sort("gender", Sort.DESCENDING)
                .asObservable();
        results
                .filter(RealmResults::isLoaded)
                .subscribe(results1 -> {
                    Log.d("TEST", "onCreate: " + results1.size());
                    //adapter.setData(results1);
                    if (results1.isEmpty()) {
                        listener.onError();
                    }else {
                        listener.onFinished(results1);
                    }
                });


    }
}
