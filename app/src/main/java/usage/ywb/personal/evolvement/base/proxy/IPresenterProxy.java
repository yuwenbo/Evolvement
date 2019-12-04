package usage.ywb.personal.evolvement.base.proxy;

import usage.ywb.personal.evolvement.base.presenter.IBasePresenter;

/**
 * @author Kingdee.ywb
 * @version [ V.2.7.1  2019/11/28 ]
 */
public interface IPresenterProxy {

    void bindPresenter();

    void unbindPresenter();

    <P extends IBasePresenter> P getPresenter(Class clazz);

}
