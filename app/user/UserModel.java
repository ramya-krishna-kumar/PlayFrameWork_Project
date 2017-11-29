package user;

import global.common.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "users",noClassnameStored = true)/**mapping the database*/
@Getter
@Setter/** lombok automatically generates get and set for setting and getting a value defaultly*/
public class UserModel extends BaseModel {
    private String name, address;
    private int age;


    public enum Fields {address, age, name}
}
