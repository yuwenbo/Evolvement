package usage.ywb.personal.evolvement.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Kingdee.ywb
 * @version [ V.2.7.1  2019/12/6 ]
 */
public class Company extends Entity {

    @SerializedName("ID")
    public long id;

    @SerializedName("NAME")
    public String name;

}
