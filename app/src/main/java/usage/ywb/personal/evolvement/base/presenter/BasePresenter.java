package usage.ywb.personal.evolvement.base.presenter;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import usage.ywb.personal.evolvement.base.ui.IBaseView;

/**
 * 通过{@link IBasePresenter}，来实现View层对Presenter的依赖，同时做了内存泄漏的预防处理。
 * Presenter{@link #getView()}来获取View层对象
 *
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class BasePresenter<V extends IBaseView> implements IBasePresenter {

    /**
     * 采用弱引用来防止内存泄漏
     */
    private WeakReference<V> mViewRef;

    public BasePresenter(@NonNull V view) {
        attachView(view);
    }

    private void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public V getView() {
        return mViewRef.get();
    }

    @Override
    public boolean isViewAttach() {
        return mViewRef != null && mViewRef.get() != null;
    }

    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }


}
