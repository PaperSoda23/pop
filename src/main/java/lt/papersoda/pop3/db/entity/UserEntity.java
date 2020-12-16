package lt.papersoda.pop3.db.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long client_id;

    @Column
    private String name;

    @Column
    private String password;

    @OneToMany(mappedBy = "client", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Fetch(value= FetchMode.SELECT)
    private List<MailEntity> mails;

    public String getPassword() {
        return password;
    }

    public List<MailEntity> getMails() {
        return mails;
    }
}
