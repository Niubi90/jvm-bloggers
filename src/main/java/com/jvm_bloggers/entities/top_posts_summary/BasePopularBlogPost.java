package com.jvm_bloggers.entities.top_posts_summary;

import com.jvm_bloggers.entities.blog_post.BlogPost;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract class BasePopularBlogPost {

    @Column(name = "position", nullable = false)
    private Integer position;

    @ManyToOne(optional = false)
    @JoinColumn(name = "blog_post_id", nullable = false)
    private BlogPost blogPost;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "top_posts_summary_id", nullable = false)
    private TopPostsSummary topPostsSummary;

    @Column(name = "number_of_clicks", nullable = false)
    private Integer numberOfClicks;

    public BasePopularBlogPost(BlogPost blogPost, Integer position, Integer numberOfClicks) {
        this.position = position;
        this.blogPost = blogPost;
        this.numberOfClicks = numberOfClicks;
    }

}
