package com.jvm_bloggers.entities.top_posts_summary;

import com.jvm_bloggers.entities.blog_post.BlogPost;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "popular_personal_post")
public class PopularPersonalPost extends BasePopularBlogPost {

    @Id
    @GeneratedValue(generator = "POPULAR_PERSONAL_POST_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "POPULAR_PERSONAL_POST_SEQ", sequenceName = "POPULAR_PERSONAL_POST_SEQ",
        allocationSize = 1)
    private Long id;

    public PopularPersonalPost(BlogPost blogPost, Integer position, Integer numberOfClicks) {
        super(blogPost, position, numberOfClicks);
    }
}
