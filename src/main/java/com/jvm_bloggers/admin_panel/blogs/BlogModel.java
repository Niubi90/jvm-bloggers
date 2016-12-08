package com.jvm_bloggers.admin_panel.blogs;

import com.jvm_bloggers.core.data_fetching.blogs.domain.Blog;
import com.jvm_bloggers.core.data_fetching.blogs.domain.BlogRepository;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class BlogModel extends LoadableDetachableModel<Blog> {

    @SpringBean
    private BlogRepository blogRepository;

    private final Long blogId;

    public BlogModel(Blog blog) {
        super(blog);
        Injector.get().inject(this);
        blogId = blog.getId();
    }

    @Override
    protected Blog load() {
        return blogRepository.findOne(blogId);
    }
}
