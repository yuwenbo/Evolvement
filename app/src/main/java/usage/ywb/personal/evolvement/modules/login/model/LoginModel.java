package usage.ywb.personal.evolvement.modules.login.model;

import android.os.SystemClock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import usage.ywb.personal.evolvement.base.model.BaseModel;
import usage.ywb.personal.evolvement.base.utils.ReflectMethodAdapterFactory;
import usage.ywb.personal.evolvement.entity.User;
import usage.ywb.personal.evolvement.modules.login.LoginContract;
import usage.ywb.personal.evolvement.modules.login.presenter.LoginPresenter;

/**
 * Model层实现登录
 *
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class LoginModel extends BaseModel implements LoginContract.LoginModel<LoginPresenter> {

    @Override
    public void login(final User user, final LoginPresenter presenter) {
        //处理网络请求数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO
                SystemClock.sleep(1500);
                JsonObject json = new JsonObject();
                json.addProperty("USERNAME", "张三");
                json.addProperty("ALIAS", "小33");
                json.addProperty("AGE", 27);

                JsonObject company = new JsonObject();
                company.addProperty("ID", 134781199L);
                company.addProperty("NAME", "金小蝶");
                company.addProperty("ORG", 8888);

                JsonArray array = new JsonArray();
                array.add(company);

                json.add("COMPANY", array);

                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapterFactory(new ReflectMethodAdapterFactory());
                Gson gson = builder.create();

                User user = gson.fromJson(json, User.class);

                presenter.onLoginSucceed(user);
            }
        }).start();
    }


}
