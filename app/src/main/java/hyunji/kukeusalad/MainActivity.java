package hyunji.kukeusalad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import adapter.PersonListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import vo.KukeuPerson;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.profile_img)
    ImageView vProfileImg;

    @BindView(R.id.toolbar)
    Toolbar vToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView vRecyclerView;

    private List<KukeuPerson> kukeuPersonList;
    private PersonListAdapter personListAdapter;
    //private RecyclerView vRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //vRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        kukeuPersonList = new ArrayList<>();


        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());
        kukeuPersonList.add(new KukeuPerson());

        personListAdapter = new PersonListAdapter(kukeuPersonList);
        vRecyclerView.setAdapter(personListAdapter);
        vRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


       personListAdapter.notifyDataSetChanged();



//        vRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        persons = new ArrayList<>();
//
//        persons.add(new Person());
//        persons.add(new Person());
//        persons.add(new Person());
//        persons.add(new Person());
//        persons.add(new Person());
//        persons.add(new Person());
//
//
//        personListAdapter = new PersonListAdapter(persons);
//        vRecyclerView.setAdapter(personListAdapter);
//
//        vRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

}
