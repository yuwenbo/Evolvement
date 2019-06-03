package usage.ywb.personal.evolvement.base;

/**
 * @author Kingdee.ywb
 * @version [ V.2.4.7  2019/3/15 ]
 */
public interface IResponseListener<T, R> {

    void onResponseSucceed(T tag, R result);

    void onResponseFailure(IHttpError error);

}
