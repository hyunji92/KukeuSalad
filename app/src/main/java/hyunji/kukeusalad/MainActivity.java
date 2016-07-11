package hyunji.kukeusalad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        kukeuPersonList = new ArrayList<>();

        for (int i=0; i<20 ; i++){

            kukeuPersonList.add(new KukeuPerson());
        }

        personListAdapter = new PersonListAdapter(kukeuPersonList);
        vRecyclerView.setAdapter(personListAdapter);
        vRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



    }

}
