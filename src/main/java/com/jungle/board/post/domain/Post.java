package com.jungle.board.post.domain;

import com.jungle.board.board.domain.Board;
import com.jungle.board.comment.domain.Comment;
import com.jungle.board.common.BaseTimeEntity;
import com.jungle.board.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private int likeCount;

    private int viewCount;

    private String status;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "like",
//            joinColumns = @JoinColumn(name = "postId"),
//            inverseJoinColumns = @JoinColumn(name = "userId")
//    )
//    private List<User> likedUsers;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "bookmark",
//            joinColumns = @JoinColumn(name = "postId"),
//            inverseJoinColumns = @JoinColumn(name = "userId")
//    )
//    private List<User> bookmarkedUsers;
}
