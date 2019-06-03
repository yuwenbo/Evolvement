package usage.ywb.personal.evolvement.modules.company;

import android.support.annotation.NonNull;

import usage.ywb.personal.evolvement.base.BasePresenter;
import usage.ywb.personal.evolvement.base.IHttpError;
import usage.ywb.personal.evolvement.base.IResponseListener;
import usage.ywb.personal.evolvement.modules.Company;
import usage.ywb.personal.evolvement.modules.Contract;
import usage.ywb.personal.evolvement.modules.User;
import usage.ywb.personal.evolvement.modules.login.LoginModel;

/**
 * @author Kingdee.ywb
 * @version [ V.2.4.7  2019/3/15 ]
 */
public class CompanyPresenter extends BasePresenter<Contract.LoginView> implements Contract.CompanyPresenter {

    private Contract.CompanyModel companyModel;

    public CompanyPresenter(@NonNull Contract.LoginView view) {
        super(view);
        companyModel = new CompanyModel();
    }

    @Override
    public void getCompany(User user) {
        getView().showLoading();
        companyModel.getCompany(user, new IResponseListener<Contract.CompanyPresenter, Company>() {
            @Override
            public void onResponseSucceed(Contract.CompanyPresenter tag, Company result) {
                getView().hideLoading();
            }

            @Override
            public void onResponseFailure(IHttpError error) {
                getView().hideLoading();
            }
        });
    }

}
