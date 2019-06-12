package usage.ywb.personal.evolvement.base.ui;

import usage.ywb.personal.evolvement.base.presenter.IDelegate;

/**
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public interface IBaseView extends IDelegate {

    /**
     * 用以显示正在加载中的
     */
    void showLoading();

    /**
     * 隐藏正在加载中的框
     */
    void hideLoading();

    /**
     * 显示错误信息
     */
    void showError();

    /**
     * 隐藏错误信息
     */
    void hideError();

    /**
     * 显示空页面
     */
    void showEmpty();

    /**
     * 隐藏空页面元素
     */
    void hideEmpty();

    void showShortToast(String msg);

    void showLongToast(String msg);

}
