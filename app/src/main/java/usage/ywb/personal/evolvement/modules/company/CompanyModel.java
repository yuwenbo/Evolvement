package usage.ywb.personal.evolvement.modules.company;

import android.os.SystemClock;

import usage.ywb.personal.evolvement.base.IHttpError;
import usage.ywb.personal.evolvement.base.IResponseListener;
import usage.ywb.personal.evolvement.modules.Company;
import usage.ywb.personal.evolvement.modules.Contract;
import usage.ywb.personal.evolvement.modules.User;

/**
 * @author Kingdee.ywb
 * @version [ V.2.4.9  2019/4/28 ]
 */
public class CompanyModel implements Contract.CompanyModel, IHttpError {

    @Override
    public void getCompany(User user, final IResponseListener<Contract.CompanyPresenter, Company> listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                listener.onResponseFailure(CompanyModel.this);
            }
        }).start();
    }

}
