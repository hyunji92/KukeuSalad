package hyunji.kukeusalad.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hyunji.kukeusalad.R;
import hyunji.kukeusalad.model.KukeuPerson;
import hyunji.kukeusalad.view.HeaderItemView;
import hyunji.kukeusalad.view.ListItemBoyView;
import hyunji.kukeusalad.view.ListItemView;
import hyunji.kukeusalad.view.MiddleItemView;
import io.realm.Realm;

/**
 * Created by hyunji on 16. 7. 10..
 */


public class PersonListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<KukeuPerson> kukeuPersonList = new ArrayList<>();
    private Realm realm;

    private boolean showMiddle = false;

    public final int VIEW_TYPE_GIRL = 0;
    public final int VIEW_TYPE_BOY = 1;
    public final int VIEW_TYPE_HEADER = 2;
    public final int VIEW_TYPE_MIDDLE = 3;


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
            return new HeaderViewHolder(new HeaderItemView(context));
        } else if (viewType == VIEW_TYPE_MIDDLE) {
            return new MiddleViewHolder(new MiddleItemView(context));
        } else {
            return new GirlViewHolder(new ListItemView(context));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);

        switch (viewType) {
            case VIEW_TYPE_GIRL:
                ((GirlViewHolder) holder).listItemView.setData(kukeuPersonList.get(position));

                ((GirlViewHolder) holder).girlBtn.setOnClickListener(v -> {
                    //do button click work here
                    long id = kukeuPersonList.get(position).getId();
                    KukeuPerson kukeuPerson = realm.where(KukeuPerson.class).equalTo("id", id).findFirst();

                    realm.executeTransaction(realm1 -> {
                        // 하나의 객체를 삭제합니다
                        kukeuPerson.deleteFromRealm();
                    });
                });
                //((GirlViewHolder) holder).girlImg.set

                break;
            case VIEW_TYPE_BOY:
                ((BoyViewHolder) holder).listItemBoyView.setData(kukeuPersonList.get(position));
                ((BoyViewHolder) holder).boyBtn.setOnClickListener(v -> {

                    //do button click work here
                    long id = kukeuPersonList.get(position).getId();
                    KukeuPerson kukeuPerson = realm.where(KukeuPerson.class).equalTo("id", id).findFirst();

                    realm.executeTransaction(realm1 -> {
                        // 하나의 객체를 삭제합니다
                        kukeuPerson.deleteFromRealm();
                    });
                });

                break;
        }
    }

    @Override
    public int getItemViewType(int position) {

        Log.d("HeaderView", "getItemViewType: " + showMiddle);
        if ("".equals(kukeuPersonList.get(position).getName())) {

                return VIEW_TYPE_HEADER;
////            } else {
////                return VIEW_TYPE_MIDDLE;
//            }


        }
        if ("girl".equals(kukeuPersonList.get(position).getGender())) {
            return VIEW_TYPE_GIRL;
        }

        return VIEW_TYPE_BOY;

    }

    @Override
    public int getItemCount() {
        return kukeuPersonList.size();
    }


    public void setData(List<KukeuPerson> data) {
        kukeuPersonList.clear();
        kukeuPersonList.addAll(data);
        notifyDataSetChanged();
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


    public void setOnItemClickListener(ClickListener clickListener) {
        PersonListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        final HeaderItemView headertemView;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            headertemView = (HeaderItemView) itemView;
        }
    }

    public class MiddleViewHolder extends RecyclerView.ViewHolder {
        final MiddleItemView middleItemView;

        public MiddleViewHolder(View itemView) {
            super(itemView);

            middleItemView = (MiddleItemView) itemView;
        }
    }


}
