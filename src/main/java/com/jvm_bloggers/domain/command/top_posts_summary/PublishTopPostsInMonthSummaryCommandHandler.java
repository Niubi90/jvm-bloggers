package com.jvm_bloggers.domain.command.top_posts_summary;

import com.jvm_bloggers.domain.command.CommandHandler;
import com.jvm_bloggers.entities.blog_post.BlogPost;
import com.jvm_bloggers.entities.blog_post.BlogPostRepository;
import com.jvm_bloggers.entities.click.ClickRepository;
import com.jvm_bloggers.entities.click.PostIdWithCount;
import com.jvm_bloggers.entities.top_posts_summary.PopularPersonalPost;
import com.jvm_bloggers.entities.top_posts_summary.TopPostsSummary;
import com.jvm_bloggers.entities.top_posts_summary.TopPostsSummaryRepository;
import com.jvm_bloggers.utils.NowProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.jvm_bloggers.entities.blog.BlogType.PERSONAL;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class PublishTopPostsInMonthSummaryCommandHandler implements CommandHandler<PublishTopPostsInMonthSummary> {

    private final TopPostsSummaryRepository topPostsSummaryRepository;
    private final BlogPostRepository blogPostRepository;

    @Override
    @EventListener
    @Transactional
    public void handle(PublishTopPostsInMonthSummary command) {

        command.getBestPersonalPosts()
            .zipWithIndex()
            .map(postWithIndex -> {
                long position = postWithIndex._2 +1;
                PostIdWithCount postIdWithCount = postWithIndex._1;
                BlogPost post = blogPostRepository.getOne(postIdWithCount.getBlogPostId());
                new PopularPersonalPost(post, position, postIdWithCount.getCount());
        })

        new TopPostsSummary()


    }

}
