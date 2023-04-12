package one.udayyadav.springsec.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class AppUser {

    @Id
    private String sub;
    private String name;
    private String email;

}
