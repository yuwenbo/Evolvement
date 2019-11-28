package usage.ywb.personal.evolvement.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import usage.ywb.personal.evolvement.base.presenter.IBasePresenter;
import usage.ywb.personal.evolvement.widgets.LoadingDialog;

/**
 * 通过{@link IBasePresenter}，来实现View层对Presenter的依赖，同时做了内存泄漏的预防处理。
 * Activity通过{@link #getPresenter(Class)}来调用Presenter，支持多种Presenter的情况。
 *
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/14 ]
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private Map<String, IBasePresenter> mPresenters;

    private LoadingDialog loadingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenters = new HashMap<>();
    }

    /**
     * 懒加载获取Presenter
     *
     * @param clazz {@link IBasePresenter}的实现类，要返回的Presenter类
     * @param <P>   {@link IBasePresenter}的实现类，返回的Presenter类
     * @return 返回 presenter对象
     */
    @SuppressWarnings("unchecked")
    public <P extends IBasePresenter> P getPresenter(Class clazz) {
        if (!IBasePresenter.class.isAssignableFrom(clazz)) {
            throw new RuntimeException(clazz.getSimpleName() + " 不是 IBasePresenter 的实现类或子类");
        }
        IBasePresenter presenter = mPresenters.get(clazz.getName());
        if (presenter != null) {
            return (P) presenter;
        }
        try {
            Constructor[] constructors = clazz.getConstructors();
            for (Constructor constructor : constructors) {
                Class<?>[] classes = constructor.getParameterTypes();
                //只有一个参数的构造方法，且参数类型为IBaseView
                if (classes.length == 1 && IBaseView.class.isAssignableFrom(classes[0])) {
                    presenter = (P) constructor.newInstance(this);
                    mPresenters.put(clazz.getName(), presenter);
                }
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        }
        if (presenter == null) {
            throw new RuntimeException("没有找到合适的构造方法");
        }
        return (P) presenter;
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
        if (mPresenters != null && !mPresenters.isEmpty()) {
            for (IBasePresenter presenter : mPresenters.values()) {
                if (presenter != null && presenter.isViewAttach()) {
                    presenter.detachView();
                }
            }
            mPresenters.clear();
        }
        mPresenters = null;
        super.onDestroy();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Activity> T getActivity() {
        return (T) this;
    }


}
