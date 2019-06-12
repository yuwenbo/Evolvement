package usage.ywb.personal.evolvement.modules.login.presenter;

import android.support.annotation.NonNull;

import usage.ywb.personal.evolvement.base.presenter.BasePresenter;
import usage.ywb.personal.evolvement.base.nets.IHttpError;
import usage.ywb.personal.evolvement.base.nets.IResponseListener;
import usage.ywb.personal.evolvement.modules.login.LoginContract;
import usage.ywb.personal.evolvement.entity.User;
import usage.ywb.personal.evolvement.modules.login.model.LoginModel;

/**
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenter {

    private LoginContract.LoginModel loginModel;

    public LoginPresenter(@NonNull LoginContract.LoginView view) {
        super(view);
        loginModel = new LoginModel();
    }

    @Override
    public void login(User user) {
        getView().showLoading();
        loginModel.login(user, new IResponseListener<LoginContract.LoginPresenter, User>() {
            @Override
            public void onResponseSucceed(LoginContract.LoginPresenter loginPresenter, User user) {
                getView().hideLoading();
                getView().onLoginSucceed();
            }

            @Override
            public void onResponseFailure(IHttpError error) {
                getView().hideLoading();
                getView().showShortToast("登录失败");
                getView().onLoginFailure();
            }
        });
    }

}
