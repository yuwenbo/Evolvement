package usage.ywb.personal.evolvement.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import usage.ywb.personal.evolvement.widgets.LoadingDialog;

/**
 * 通过{@link IBasePresenter}，来实现View层对Presenter的依赖，同时做了内存泄漏的预防处理。
 * Activity通过{@link #getPresenter()}来调用Presenter。
 *
 * @author Kingdee.ywb
 * @version [ V.2.4.7  2019/3/14 ]
 */
public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    private List<IBasePresenter> presenters;

    private P presenter;

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected abstract P getPresenterInstance();

    /**
     * 懒加载
     *
     * @return 返回 presenter
     */
    public P getPresenter() {
        if (presenter == null) {
            presenter = this.getPresenterInstance();
        }
        return presenter;
    }

    @Override
    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void hideError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void hideEmpty() {

    }

    @Override
    public void showShortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenters != null && !presenters.isEmpty()) {
            for (IBasePresenter presenter : presenters) {
                if (presenter != null && presenter.isViewAttach()) {
                    presenter.detachView();
                }
            }
            presenters.clear();
            presenters = null;
        }
        if (presenter != null && presenter.isViewAttach()) {
            presenter.detachView();
        }

    }

    @Override
    public <T extends Activity> T getActivity() {
        return (T) this;
    }


}
