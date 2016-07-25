package hyunji.kukeusalad.view.fragment;

import java.util.List;

import hyunji.kukeusalad.model.KukeuPerson;

/**
 * Created by hyunji on 16. 7. 22..
 */
public interface MainView {

    void TextToast(String toast);
    void setItem(List<KukeuPerson> items);
}
