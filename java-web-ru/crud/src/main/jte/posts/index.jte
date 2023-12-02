@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <main>
                <h1>Все посты</h1>

                <table class="table table-striped table-responsive">
                    <thead>
                      <tr>
                        <th scope="col">Пост</th>
                      </tr>
                    </thead>
                    <tbody>
                        @for(var post : page.getPosts())
                            <tr>
                                <td>
                                    <a href="${NamedRoutes.postsShowPath(post.getId())}">${post.getName()}</a>
                                </td>
                            </tr>
                        @endfor
                    </tbody>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <div class="btn-group">
                            @if(page.isHasPreviousPage())
                                <a href="${NamedRoutes.postsWithPaginationPath(page.getPage() - 1)}" class="btn btn-primary btn-sm">Назад</a>
                            @else
                                <a href="" class="btn btn-primary btn-sm disabled">Назад</a>
                            @endif
                            @if(page.isHasNextPage())
                                <a href="${NamedRoutes.postsWithPaginationPath(page.getPage() + 1)}" class="btn btn-primary btn-sm">Вперед</a>
                            @else
                                <a href="" class="btn btn-primary btn-sm disabled">Вперед</a>
                            @endif
                        </div>
                    </div>
                </table>
            </main>
        </div>
    `
)
