package model;

import javax.persistence.*;

@Entity
@Table(name = "Hibernate_Photos")
public class Photos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private int photoId;

    @Column(name = "post_id")
    private int postId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="post_id")
    private Post myPost;




}
