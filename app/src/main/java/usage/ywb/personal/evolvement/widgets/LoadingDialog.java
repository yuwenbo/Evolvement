package usage.ywb.personal.evolvement.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import usage.ywb.personal.evolvement.R;

/**
 * @author Kingdee.ywb
 * @version [ V.2.4.7  2019/3/14 ]
 */
public class LoadingDialog extends Dialog {

    private CharSequence message;//内容

    public LoadingDialog(@NonNull Context context) {
        this(context, 0);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public void setMessage(CharSequence message) {
        this.message = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common_loading);
        setCancelable(false);
        TextView contentTv = findViewById(R.id.content_tv);
        if (message != null) {
            contentTv.setText(message);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
