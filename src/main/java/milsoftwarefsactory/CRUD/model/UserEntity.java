package milsoftwarefsactory.CRUD.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.data.repository.CrudRepository;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "users")
public class UserEntity {
// Initialize variables

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

// Setup date
    //@Column(columnDefinition = "date")
    //@JsonFormat(pattern = "yyyy-MM-DD")
    //private Date deliveredOn;



    // Getters and Setters for variables in class
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

}
