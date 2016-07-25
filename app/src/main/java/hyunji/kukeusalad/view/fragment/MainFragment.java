package hyunji.kukeusalad.view.fragment;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hyunji.kukeusalad.R;
import hyunji.kukeusalad.adapter.PersonListAdapter;
import hyunji.kukeusalad.model.KukeuPerson;
import hyunji.kukeusalad.presenter.MainPresenter;
import hyunji.kukeusalad.presenter.MainPresenterlmpl;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;

/**
 * Created by hyunji
 */
public class MainFragment extends Fragment implements MainView{

    @BindView(R.id.content_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolBar;

    private Realm realm;
    private PersonListAdapter adapter;

    private Context context = null;
    private MainPresenter presenter;

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

        presenter = new MainPresenterlmpl(this);

        adapter = new PersonListAdapter(context);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((position, v) -> Log.d("TEST", "onItemClick position: " + position));

        realm = Realm.getDefaultInstance();

        RealmResults<KukeuPerson> realmResults = realm.where(KukeuPerson.class).findAll();

        if (realmResults.isEmpty()) {
            presenter.loadDummyData();
        }


        return view;
    }



    @Override
    public void TextToast(String toast) {
        Toast.makeText(getContext(),toast,Toast.LENGTH_LONG).show();
    }

    @Override
    public void setItem(List<KukeuPerson> items) {
        adapter.setData(items);
        adapter.notifyDataSetChanged();

    }
}
