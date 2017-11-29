package loginprocess;

import global.common.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Entity;

@Entity(value = "login", noClassnameStored = true)
/**mapping the database*/
@Getter
@Setter
/** lombok automatically generates get and set for setting and getting a value defaultly*/
public class LoginProcessModel extends BaseModel {

   //for signup and signin
    private String userid, password,username;
    //for posting a blog





    public enum Fields {userid, password,username}
}
