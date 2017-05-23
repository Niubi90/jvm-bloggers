package com.jvm_bloggers.schedulers.top_posts_summary;

import com.jvm_bloggers.domain.command.CommandPublisher;
import com.jvm_bloggers.domain.command.top_posts_summary.PublishTopPostsInMonthSummary;
import com.jvm_bloggers.domain.query.MostPopularBlogPostsQuery;
import com.jvm_bloggers.entities.click.PostIdWithCount;
import com.jvm_bloggers.utils.NowProvider;
import javaslang.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TopPostsInPreviousMonthSummaryPublisher {

    private static final int NUMBER_OF_TOP_PERSONAL_POSTS = 10;
    private static final int NUMBER_OF_TOP_COMPANY_POSTS = 5;

    private final CommandPublisher commandPublisher;
    private final MostPopularBlogPostsQuery query;
    private final NowProvider nowProvider;

    @Scheduled(fixedDelay = 10 * 1000L)
    public void calculateTopPostsInPreviousMonth() {

        LocalDateTime startOfPreviousMonth = calculateStartDate();
        LocalDateTime middleOfThisMonth = calculateEndDate();

        List<PostIdWithCount> bestPersonalPosts = query.getBestPersonalPosts(
            startOfPreviousMonth,
            middleOfThisMonth,
            NUMBER_OF_TOP_PERSONAL_POSTS
        );

        List<PostIdWithCount> bestCompanyPosts = query.getBestCompanyPosts(
            startOfPreviousMonth,
            middleOfThisMonth,
            NUMBER_OF_TOP_COMPANY_POSTS
        );

        YearMonth yearMonth = YearMonth.from(startOfPreviousMonth);
        commandPublisher.publish(new PublishTopPostsInMonthSummary(
            bestPersonalPosts, bestCompanyPosts, yearMonth
        ));
    }

    private LocalDateTime calculateStartDate() {
        return nowProvider.today()
            .minusMonths(1)
            .withDayOfMonth(1)
            .atStartOfDay();
    }

    private LocalDateTime calculateEndDate() {
        return nowProvider.today()
            .withDayOfMonth(16)
            .atStartOfDay();
    }

}
