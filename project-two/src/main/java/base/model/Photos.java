package base.model;

import javax.persistence.*;

//Photo model
@Entity
@Table(name = "Photos")
public class Photos {

    //Auto generated serial number and primary key of Hibernate_Photos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private int photoId;

    //Column photo string
    @Column(name = "photobyte")
    private String photoString;

    //Many photos can be attached to one post
    //Connected to Post in the @JoinColumn portion with a new column named post_id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="post_id")
    private Post myPost;




}
