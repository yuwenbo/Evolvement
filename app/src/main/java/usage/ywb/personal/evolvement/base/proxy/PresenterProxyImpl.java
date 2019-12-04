package usage.ywb.personal.evolvement.base.proxy;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import usage.ywb.personal.evolvement.base.presenter.IBasePresenter;
import usage.ywb.personal.evolvement.base.presenter.InjectPresenter;
import usage.ywb.personal.evolvement.base.ui.IBaseView;

/**
 * @author Kingdee.ywb
 * @version [ V.2.7.1  2019/12/2 ]
 */
public class PresenterProxyImpl implements IPresenterProxy {


    private Map<String, IBasePresenter> mPresenters;

    private IBaseView baseView;

    public PresenterProxyImpl(IBaseView baseView) {
        this.baseView = baseView;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <P extends IBasePresenter> P getPresenter(Class clazz) {
        if (!IBasePresenter.class.isAssignableFrom(clazz)) {
            throw new RuntimeException(clazz.getSimpleName() + " 必须是 IBasePresenter 的实现类或子类");
        }
        IBasePresenter presenter = null;
        if (mPresenters == null) {
            mPresenters = new HashMap<>();
        } else {
            presenter = mPresenters.get(clazz.getName());
            if (presenter != null) {
                return (P) presenter;
            }
        }
        try {
            presenter = (IBasePresenter) clazz.newInstance();
            mPresenters.put(clazz.getName(), presenter);
        } catch (IllegalAccessException | InstantiationException e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        }
        if (presenter == null) {
            throw new RuntimeException("没有找到合适的构造方法");
        }
        return (P) presenter;
    }

    @Override
    public void bindPresenter() {
        Field[] fields = baseView.getClass().getDeclaredFields();
        if (fields.length == 0) {
            return;
        }
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(InjectPresenter.class);
            if (annotation != null) {
                try {
                    field.setAccessible(true);
                    IBasePresenter<IBaseView> presenter = getPresenter(field.getType());
                    if (presenter != null) {
                        presenter.attachView(baseView);
                    }
                    field.set(baseView, presenter);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void unbindPresenter() {
        if (mPresenters != null && !mPresenters.isEmpty()) {
            for (IBasePresenter presenter : mPresenters.values()) {
                if (presenter != null) {
                    presenter.detachView();
                }
            }
            mPresenters.clear();
        }
        mPresenters = null;
    }

}
