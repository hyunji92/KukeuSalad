package hyunji.kukeusalad.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hyunji.kukeusalad.R;
import hyunji.kukeusalad.model.KukeuPerson;
import hyunji.kukeusalad.presenter.MainPresenter;
import hyunji.kukeusalad.view.ListItemBoyView;
import hyunji.kukeusalad.view.ListItemView;
import hyunji.kukeusalad.view.fragment.MainView;
import io.realm.Realm;

/**
 * Created by hyunji
 */


public class PersonListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements MainPresenter{

    private List<KukeuPerson> kukeuPersonList = new ArrayList<>();

    private Realm realm;

    private int boyHeaderNum = -1;

    public final int VIEW_TYPE_GIRL = 0;
    public final int VIEW_TYPE_BOY = 1;
    public final int VIEW_TYPE_HEADER = 2;
    public final int VIEW_TYPE_MIDDLE = 3;

    int[] girlImgs = new int[] {R.drawable.girl_1, R.drawable.girl_2, R.drawable.girl_3, R.drawable.girl_4 ,R.drawable.girl_5};
    int[] boyImgs = new int[] {R.drawable.boy_1, R.drawable.boy_2, R.drawable.boy_3, R.drawable.boy_4 ,R.drawable.boy_5};

    private Context context;

    public PersonListAdapter(Context context) {

        this.context = context;
    }

    private static ClickListener clickListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        realm = Realm.getDefaultInstance();

        if (viewType == VIEW_TYPE_GIRL) {
            return new GirlViewHolder(new ListItemView(context));
        } else if (viewType == VIEW_TYPE_BOY) {
            return new BoyViewHolder(new ListItemBoyView(context));
        } else if (viewType == VIEW_TYPE_HEADER) {
            View v = LayoutInflater.from(context).inflate(R.layout.list_header, parent, false);
            return new GirlViewHeader(v);
        } else if (viewType == VIEW_TYPE_MIDDLE) {
            View v = LayoutInflater.from(context).inflate(R.layout.list_middle, parent, false);
            return new BoyViewHeader(v);
        } else {
            return new GirlViewHolder(new ListItemView(context));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);

        switch (viewType) {
            case VIEW_TYPE_HEADER:


                break;
            case VIEW_TYPE_GIRL:
                ((GirlViewHolder) holder).listItemView.setData(kukeuPersonList.get(position - 1));
                ((GirlViewHolder) holder).girlBtn.setOnClickListener(v -> {
                    //do button click work here
                    long id = kukeuPersonList.get(position - 1).getId();
                    KukeuPerson kukeuPerson = realm.where(KukeuPerson.class).equalTo("id", id).findFirst();

                    realm.executeTransaction(realm1 -> {
                        // 하나의 객체를 삭제합니다

                        kukeuPerson.deleteFromRealm();
                        boyHeaderNum = realm1.where(KukeuPerson.class).equalTo("gender", "girl").findAll().size() + 1;
                    });
                });
                int girlImgId = (int)(Math.random() * girlImgs.length);
                ((GirlViewHolder) holder).girlImg.setBackgroundResource(girlImgs[girlImgId]);

                break;
            case VIEW_TYPE_BOY:
                ((BoyViewHolder) holder).listItemBoyView.setData(kukeuPersonList.get(position - 2));
                ((BoyViewHolder) holder).boyBtn.setOnClickListener(v -> {

                    //do button click work here
                    long id = kukeuPersonList.get(position - 2).getId();
                    KukeuPerson kukeuPerson = realm.where(KukeuPerson.class).equalTo("id", id).findFirst();

                    realm.executeTransaction(realm1 -> {
                        // 하나의 객체를 삭제합니다
                        kukeuPerson.deleteFromRealm();
                    });
                });
                int boyImgId = (int)(Math.random() * boyImgs.length);
                ((BoyViewHolder) holder).boyImg.setBackgroundResource(boyImgs[boyImgId]);

                break;
        }
    }

    @Override
    public int getItemViewType(int position) {


        if (position == 0)
            return VIEW_TYPE_HEADER;

        if(position - 1 < kukeuPersonList.size()) {
            if ("girl".equals(kukeuPersonList.get(position - 1).getGender())) {
                return VIEW_TYPE_GIRL;
            }
            if ("boy".equals(kukeuPersonList.get(position - 1).getGender())) {
                if (boyHeaderNum == -1) {
                    boyHeaderNum = position;
                    return VIEW_TYPE_MIDDLE;
                }
                if(boyHeaderNum == position)
                    return VIEW_TYPE_MIDDLE;
            }
        }

        return VIEW_TYPE_BOY;

    }

    @Override
    public int getItemCount() {
        return kukeuPersonList.size() + 2;
    }


    public void setData(List<KukeuPerson> data) {
        kukeuPersonList.clear();
        kukeuPersonList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void loadDummyData() {

    }

    @Override
    public void onItemsClicked(int position) {

    }

    public class BoyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ListItemBoyView listItemBoyView;
        @BindView(R.id.boy_btn)
        Button boyBtn;

        @BindView(R.id.boy_img)
        ImageView boyImg;

        public BoyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            listItemBoyView = (ListItemBoyView) itemView;
            listItemBoyView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }


    public class GirlViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ListItemView listItemView;

        @BindView(R.id.girl_btn)
        Button girlBtn;

        @BindView(R.id.girl_img)
        ImageView girlImg;

        public GirlViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            listItemView = (ListItemView) itemView;
            listItemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    class GirlViewHeader extends RecyclerView.ViewHolder {
        TextView txtTitle;

        public GirlViewHeader(View itemView) {
            super(itemView);
            this.txtTitle = (TextView) itemView.findViewById(R.id.girl_header_text);
        }
    }


    private class BoyViewHeader extends RecyclerView.ViewHolder {
        TextView txtTitle;

        public BoyViewHeader(View itemView) {
            super(itemView);
            this.txtTitle = (TextView) itemView.findViewById(R.id.boy_header_text);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        PersonListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

    }
}
