package com.jvm_bloggers.entities.blog_post;

import com.jvm_bloggers.entities.blog.BlogType;
import javaslang.control.Option;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    Option<BlogPost> findByUrl(String url);

    Option<BlogPost> findByUid(String uid);

    List<BlogPost> findByApprovedDateAfterAndApprovedTrueOrderByApprovedDateAsc(
        LocalDateTime publishedDate);

    List<BlogPost> findByApprovedTrueAndBlogAuthorNotInOrderByApprovedDateDesc(
        Pageable page, Set<String> excludedAuthors);

    @Query("from BlogPost bp order by "
           + "case when bp.approved is null then 0 else 1 end, "
           + "bp.publishedDate desc")
    List<BlogPost> findLatestPosts(Pageable page);

    int countByPublishedDateAfter(LocalDateTime publishedDate);

    int countByApprovedIsNull();

    List<BlogPost> findByBlogIdOrderByPublishedDateDesc(Long blogId, Pageable page);

    int countByBlogId(Long blogId);

//    @Query(value = "select bp, c.blog_post_id, b.blog_type, count(c.*) as clicks_count "
//        + " from "
//        + "    click c left join blog_post bp on (c.blog_post_id=bp.id) left join blog b on (bp.blog_id=b.id) "
//        + " where "
//        + "    c.click_date >= :fromValue and "
//        + "    c.click_date <= :toValue and "
//        + "    b.blog_type= :typeValue "
//        + " group by "
//        + "    c.blog_post_id, bp.title, bp.url, bp.approved_date, b.blog_type "
//        + " order by clicks_count desc limit :limitValue", nativeQuery = true)
//    List<Object> calculateMostPopularBlogPosts(
//        @Param("fromValue") LocalDateTime from,
//        @Param("toValue") LocalDateTime to,
//        @Param("typeValue") BlogType personal,
//        @Param("limitValue") int limitValue);



}
