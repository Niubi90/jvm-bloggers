package com.jvm_bloggers.domain.query;

import com.jvm_bloggers.entities.blog.BlogType;
import com.jvm_bloggers.entities.click.ClickRepository;
import com.jvm_bloggers.entities.click.PostIdWithCount;
import javaslang.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.jvm_bloggers.entities.blog.BlogType.COMPANY;
import static com.jvm_bloggers.entities.blog.BlogType.PERSONAL;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MostPopularBlogPostsQuery {

    private final ClickRepository clickRepository;

    public List<PostIdWithCount> getBestPersonalPosts(LocalDateTime startDate, LocalDateTime endDate, int count) {
        return getBestPosts(startDate, endDate, PERSONAL, count);
    }

    public List<PostIdWithCount> getBestCompanyPosts(LocalDateTime startDate, LocalDateTime endDate, int count) {
        return getBestPosts(startDate, endDate, COMPANY, count);
    }

    private List<PostIdWithCount> getBestPosts(LocalDateTime startDate, LocalDateTime endDate, BlogType type, int count) {
        return clickRepository.calculateMostPopularBlogPosts(
            startDate,
            endDate,
            type,
            new PageRequest(0, count)
        );
    }


}
