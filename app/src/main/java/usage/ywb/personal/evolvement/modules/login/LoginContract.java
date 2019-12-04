package usage.ywb.personal.evolvement.modules.login;


import usage.ywb.personal.evolvement.base.ui.IBaseView;
import usage.ywb.personal.evolvement.entity.User;

/**
 * 登录的 MVP “代理” 类
 *
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class LoginContract {

    /**
     * 登录UI层接口
     *
     * @param <T>
     */
    public interface LoginView<T> extends IBaseView {

        /**
         * 登陆成功的回调
         *
         * @param t 对象
         */
        void onLoginSucceed(T t);

        /**
         * 登录失败
         */
        void onLoginFailure();

    }

    /**
     * 登录Presenter层接口
     */
    public interface LoginPresenter {
        /**
         * Presenter登录接口，UI与Model的桥梁
         *
         * @param user 登录用户
         */
        void login(User user);
    }

    /**
     * 登录Model层接口
     *
     * @param <P>
     */
    public interface LoginModel<P extends LoginContract.LoginPresenter> {
        /**
         * 登录，Model与NET直接访问的接口
         *
         * @param user      登录用户
         * @param presenter Presenter对象，用以登录结果回传
         */
        void login(User user, P presenter);
    }


}
