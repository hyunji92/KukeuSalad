package hyunji.kukeusalad.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hyunji.kukeusalad.R;
import hyunji.kukeusalad.model.KukeuPerson;

/**
 * Created by hyunji
 */
public class ListItemBoyView extends FrameLayout {

    @BindView(R.id.t_name)
    TextView name;
    @BindView(R.id.t_job)
    TextView job;
    @BindView(R.id.t_gender)
    TextView gender;
    private Context context;

    public ListItemBoyView(Context context) {
        this(context, null);
    }

    public ListItemBoyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListItemBoyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LayoutInflater.from(context).inflate(R.layout.list_item_boy, this, true);
        ButterKnife.bind(this);
        this.context = context;

    }

    public void setData(KukeuPerson data) {

        if (data != null) {
            name.setText(data.getName());
            job.setText(data.getJob());
            gender.setText(data.getGender());
        }
    }
}
