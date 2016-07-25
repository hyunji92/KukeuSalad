package hyunji.kukeusalad.presenter;

import java.util.List;

import hyunji.kukeusalad.model.KukeuPerson;

/**
 * Created by hyunji on 16. 7. 23..
 */
public interface RealmInteractor  {

    void initRealmData();
    void realmDataDelete(int position);
    void realmDataLoad(OnFinishedListener listener);

    interface OnFinishedListener {

        // 끝났을 때 처리
        void onFinished(List<KukeuPerson> kukeuPerson);

        // 에러가 났을 때 처리
        void onError();
    }
}
