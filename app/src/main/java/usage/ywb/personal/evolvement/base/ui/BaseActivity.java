package usage.ywb.personal.evolvement.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import usage.ywb.personal.evolvement.base.presenter.IBasePresenter;
import usage.ywb.personal.evolvement.base.proxy.IPresenterProxy;
import usage.ywb.personal.evolvement.base.proxy.PresenterProxyImpl;
import usage.ywb.personal.evolvement.widgets.LoadingDialog;

/**
 * 通过{@link IBasePresenter}，来实现View层对Presenter的依赖，同时做了内存泄漏的预防处理。
 * <p>
 * Activity可通过两种方式获取Presenter实例：
 * 1，通过{@link #getPresenter(Class)}获取Presenter，支持多种Presenter的情况。
 * 2，使用注解{@link usage.ywb.personal.evolvement.base.presenter.InjectPresenter}
 *
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/14 ]
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {


    private LoadingDialog loadingDialog;

    private IPresenterProxy mPresenterProxy;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenterProxy = new PresenterProxyImpl(this);
        mPresenterProxy.bindPresenter();
    }

    @SuppressWarnings("unchecked")
    public <P extends IBasePresenter> P getPresenter(Class clazz) {
        P presenter = mPresenterProxy.getPresenter(clazz);
        presenter.attachView(this);
        return presenter;
    }

    @Override
    public void showLoading(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        if (!TextUtils.isEmpty(msg)) {
            loadingDialog.setMessage(msg);
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
        mPresenterProxy.unbindPresenter();
        super.onDestroy();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Activity> T getActivity() {
        return (T) this;
    }


}
