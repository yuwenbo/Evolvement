package usage.ywb.personal.evolvement.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author yuwenbo
 * @version [ V.1.0.0  2019/3/15 ]
 */
public class User extends Entity {

    @SerializedName("USERNAME")
    public String username;

    private String alias;

    @SerializedName("COMPANY")
    public List<Company> company;

    @SerializedName("COMPANY")
    public void setCompany(List<Company> company) {
        this.company = company;

    }

    @SerializedName("ALIAS")
    public void setAlias(String alias) {
        this.alias = alias;
    }

}
