package usage.ywb.personal.evolvement.modules.login;


import usage.ywb.personal.evolvement.base.model.IBaseModel;
import usage.ywb.personal.evolvement.base.presenter.IBasePresenter;
import usage.ywb.personal.evolvement.base.ui.IBaseView;
import usage.ywb.personal.evolvement.base.nets.IResponseListener;
import usage.ywb.personal.evolvement.entity.User;

/**
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class LoginContract {

    public interface LoginView extends IBaseView {

        void onLoginSucceed();

        void onLoginFailure();

    }

    public interface LoginPresenter extends IBasePresenter {
        void login(User user);
    }

    public interface RegisterPresenter extends IBasePresenter {
        void register(User user);
    }

    public interface LoginModel extends IBaseModel {
        void login(User user, IResponseListener<LoginPresenter, User> listener);
    }

    public interface RegisterModel extends IBaseModel {
        void register(User user, IResponseListener<RegisterPresenter, User> listener);
    }


}
