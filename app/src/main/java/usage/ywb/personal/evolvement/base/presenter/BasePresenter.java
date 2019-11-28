package usage.ywb.personal.evolvement.base.presenter;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import usage.ywb.personal.evolvement.base.ui.IBaseView;

/**
 * 所有Presenter的基类
 * <p>
 * 实现{@link IBasePresenter}接口，以满足View层（context）对Presenter的依赖，同时采用弱引用的方式做了内存泄漏的预防处理。
 * Presenter通过{@link #getView()}来获取View层（context）对象
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

    /**
     * 建立Presenter与View的依赖关系
     *
     * @param view context
     */
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

    /**
     * 解除Presenter与View的依赖关系
     */
    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }


}
