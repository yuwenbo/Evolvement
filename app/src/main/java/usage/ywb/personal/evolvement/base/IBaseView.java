package usage.ywb.personal.evolvement.base;

/**
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public interface IBaseView extends IDelegate {

    void showLoading();

    void hideLoading();

    void showError();

    void hideError();

    void showEmpty();

    void hideEmpty();

    void showShortToast(String msg);

    void showLongToast(String msg);

}
