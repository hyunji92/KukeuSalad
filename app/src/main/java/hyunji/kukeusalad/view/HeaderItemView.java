package hyunji.kukeusalad.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import hyunji.kukeusalad.R;

/**
 * Created by hyunji on 16. 7. 16..
 */
public class HeaderItemView extends FrameLayout {
    @BindView(R.id.header)
    LinearLayout header;

    private Context context;
    public HeaderItemView(Context context) {
        this(context, null);
    }

    public HeaderItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LayoutInflater.from(context).inflate(R.layout.list_header, this, true);
        ButterKnife.bind(this);
        this.context = context;
    }
}
