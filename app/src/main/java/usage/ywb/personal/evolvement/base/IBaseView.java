package usage.ywb.personal.evolvement.base;

/**
 * @author Kingdee.ywb
 * @version [ V.2.4.7  2019/3/15 ]
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
