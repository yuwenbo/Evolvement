package usage.ywb.personal.evolvement.base;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * @author Kingdee.ywb
 * @version [ V.2.4.7  2019/3/15 ]
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
