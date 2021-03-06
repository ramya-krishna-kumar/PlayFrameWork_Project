package blog;

import global.common.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Entity;

@Entity(value = "login", noClassnameStored = true)
/**mapping the database*/
@Getter
@Setter
/** lombok automatically generates get and set for setting and getting a value defaultly*/
public class BlogModel extends BaseModel {

    //for signup and signin
    private String userid,username;
    //for posting a blog
    private  String blogname,blogdescription,comment;


    private int like;
    long epoch = System.currentTimeMillis()/1000;



    public enum Fields {userid, username,blogname,blogdescription,comment,like}
}
