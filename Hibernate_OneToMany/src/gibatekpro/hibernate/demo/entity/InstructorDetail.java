package gibatekpro.hibernate.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

    //annotate the class as an entity and map to db

    //define the fields

    //annotate the fields with db column names

    //create constructors

    //generate getter/setter methods

    //generate toString() method

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    //This is used for Bi-directional
    /*
    * CascadeType.All means all operations will be linked
    * to the Instructor
    *
    * However, you can list the operations you want to link
    * using {}
    * */
    @OneToOne(mappedBy = "instructorDetail",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private Instructor instructor;

    public InstructorDetail() {

    }

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

}
