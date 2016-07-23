package hyunji.kukeusalad.presenter;
import io.realm.Realm;
import hyunji.kukeusalad.view.fragment.MainView;

/**
 * Created by hyunji on 16. 7. 22..
 */
public class MainPresenterlmpl implements MainPresenter {

    private Realm realm;
    private MainView mainView;

    public MainPresenterlmpl(MainView mainView) {
        this.mainView = mainView;
    }


}
