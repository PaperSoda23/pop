package lt.papersoda.pop3.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "mail")
public class MailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long mail_id;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity client;
}
