package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hyunji.kukeusalad.R;
import vo.KukeuPerson;

/**
 * Created by hyunji on 16. 7. 10..
 */


public class PersonListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<KukeuPerson> kukeuPersonList;

    public PersonListAdapter(List<KukeuPerson> kukeuPersonList ) {
        this.kukeuPersonList = kukeuPersonList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder  h = (ViewHolder) holder;
        h.name.setText("정현지");
        h.job.setText("개발자");
        h.gender.setText("여성");


    }

    @Override
    public int getItemCount() {
        return kukeuPersonList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

//        private TextView name;
//        private TextView job;
//        private TextView gender;
        @BindView(R.id.t_name)
        TextView name;

        @BindView(R.id.t_job)
        TextView job;

        @BindView(R.id.t_gender)
        TextView gender;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

//            name = (TextView) itemView.findViewById(R.id.t_name);
//            job = (TextView) itemView.findViewById(R.id.t_job);
//            gender = (TextView) itemView.findViewById(R.id.t_gender);

        }
    }
}
