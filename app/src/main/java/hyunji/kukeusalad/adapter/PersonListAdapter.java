package hyunji.kukeusalad.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

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

    private boolean showMiddle;

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

                break;
            case VIEW_TYPE_BOY:
                ((BoyViewHolder) holder).listItemBoyView.setData(kukeuPersonList.get(position));
                break;
        }


    }


    @Override
    public int getItemViewType(int position) {

        if ("".equals(kukeuPersonList.get(position).getName())) {
            if (!showMiddle) {
                showMiddle = true;
                return VIEW_TYPE_HEADER;
            }

            return VIEW_TYPE_MIDDLE;
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

    public class BoyViewHolder extends RecyclerView.ViewHolder {
        final ListItemBoyView listItemBoyView;

        public BoyViewHolder(View itemView) {
            super(itemView);
            listItemBoyView = (ListItemBoyView) itemView;

        }
    }


    public class GirlViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ListItemView listItemView;

        //@BindView(R.id.girl_btn)
        Button girlBtn;

        public GirlViewHolder(View itemView) {
            super(itemView);
            listItemView = (ListItemView) itemView;
            listItemView.setOnClickListener(this);
            girlBtn = (Button) itemView.findViewById(R.id.girl_btn);
            //itemView.setOnLongClickListener(this);

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
