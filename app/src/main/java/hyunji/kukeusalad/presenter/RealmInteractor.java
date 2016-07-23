package hyunji.kukeusalad.presenter;

/**
 * Created by hyunji on 16. 7. 23..
 */
public interface RealmInteractor  {

    void realmDummyLoad();
    void realmDataLoad(OnFinishedListner listner);

    interface OnFinishedListner {

        // 끝났을 때 처리
        void onFinished();
        // 에러가 났을 때 처리
        void onError();
    }
}
