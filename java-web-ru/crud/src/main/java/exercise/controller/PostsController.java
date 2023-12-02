package exercise.controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {
        var page = ctx.queryParamAsClass("page", Integer.class)
                .check(item -> item >= 0, "Page should be positive.")
                .getOrDefault(1);

        var posts = PostRepository.getEntities();

        var nextIndex = Math.min(page * 5, posts.size());
        var firstIndex = (page * 5) - 5;

        if (firstIndex > posts.size()) {
            throw new NotFoundResponse("The next page isn't found.");
        }

        var pagePosts = posts.subList(firstIndex, nextIndex);
        var hasPreviousPage = firstIndex != 0;
        var hasNextPage = nextIndex != posts.size();

        var postsPage = new PostsPage(pagePosts, page, hasPreviousPage, hasNextPage);

        ctx.render("posts/index.jte", Collections.singletonMap("page", postsPage));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class)
                .get();

        var post = PostRepository.find(id);

        if (post.isEmpty()) {
            throw new NotFoundResponse("Page not found");
        }

        var page = new PostPage(post.get());

        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }
    // END
}
