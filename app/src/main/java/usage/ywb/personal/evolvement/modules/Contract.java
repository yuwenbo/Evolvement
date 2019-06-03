package usage.ywb.personal.evolvement.modules;


import usage.ywb.personal.evolvement.base.IBaseModel;
import usage.ywb.personal.evolvement.base.IBasePresenter;
import usage.ywb.personal.evolvement.base.IBaseView;
import usage.ywb.personal.evolvement.base.IResponseListener;

/**
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class Contract {

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

    public interface CompanyPresenter extends IBasePresenter {
        void getCompany(User user);
    }

    public interface LoginModel extends IBaseModel {
        void login(User user, IResponseListener<LoginPresenter, User> listener);
    }

    public interface CompanyModel extends IBaseModel {
        void getCompany(User user, IResponseListener<CompanyPresenter, Company> listener);
    }

    public interface RegisterModel extends IBaseModel {
        void register(User user, IResponseListener<RegisterPresenter, User> listener);
    }


}
