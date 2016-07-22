package hyunji.kukeusalad.presenter;

import hyunji.kukeusalad.fragment.MainView;

/**
 * Created by hyunji on 16. 7. 22..
 */
public class MainPresenterlmpl implements MainPresenter {

    private MainView mainView;

    public MainPresenterlmpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void dummyLoad() {
        mainView.TextToast("Jelly 흐에에에에에에ㅔ");
    }

    @Override
    public void dataLoad() {

    }
}
