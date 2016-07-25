package hyunji.kukeusalad.presenter;
import java.util.List;

import hyunji.kukeusalad.model.KukeuPerson;
import io.realm.Realm;
import hyunji.kukeusalad.view.fragment.MainView;

/**
 * Created by hyunji on 16. 7. 22..
 */
public class MainPresenterlmpl implements MainPresenter ,RealmInteractor.OnFinishedListener{

    private MainView mainView;
    private RealmInteractor realmInteractor;

    public MainPresenterlmpl(MainView mainView) {
        this.mainView = mainView;
        this.realmInteractor = new RealmInteractorlmpl();
    }



    @Override
    public void onFinished(List<KukeuPerson> kukeuPerson) {
        mainView.TextToast("Finish!!!!!!!");
        //list SetData
        mainView.setItem(kukeuPerson);
    }

    @Override
    public void onError() {
        mainView.TextToast("Error!!!!!!!!");
        realmInteractor.initRealmData();
    }

    @Override
    public void loadDummyData() {
        realmInteractor.realmDataLoad(this);
    }

    @Override
    public void onItemsClicked(int position) {

       realmInteractor.realmDataDelete(position);
    }
}
