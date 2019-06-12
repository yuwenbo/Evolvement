package usage.ywb.personal.evolvement.base.nets;

/**
 * 网络请求响应
 *
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public interface IResponseListener<T, R> {

    void onResponseSucceed(T tag, R result);

    void onResponseFailure(IHttpError error);

    

}
