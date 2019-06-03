package usage.ywb.personal.evolvement.modules.login;

import android.support.annotation.NonNull;

import usage.ywb.personal.evolvement.base.BasePresenter;
import usage.ywb.personal.evolvement.base.IHttpError;
import usage.ywb.personal.evolvement.base.IResponseListener;
import usage.ywb.personal.evolvement.modules.Contract;
import usage.ywb.personal.evolvement.modules.User;

/**
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class LoginPresenter extends BasePresenter<Contract.LoginView> implements Contract.LoginPresenter {

    private Contract.LoginModel loginModel;

    public LoginPresenter(@NonNull Contract.LoginView view) {
        super(view);
        loginModel = new LoginModel();
    }

    @Override
    public void login(User user) {
        getView().showLoading();
        loginModel.login(user, new IResponseListener<Contract.LoginPresenter, User>() {
            @Override
            public void onResponseSucceed(Contract.LoginPresenter loginPresenter, User user) {
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
