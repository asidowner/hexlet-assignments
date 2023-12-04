<!-- BEGIN -->
@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
    page = page,
    content = @`
    <h1>Все посты</h1>
    <div class="mb-3">
        <a href="${NamedRoutes.buildPostPath()}">Создать новый пост</a>
    </div>
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
              <tr>
                <th scope="col">Пост</th>
              </tr>
            </thead>
            <tbody>
            @for(var item : page.getPosts())
              <tr>
                <td><a href="${NamedRoutes.postPath(item.getId())}">${item.getName()}</a></td>
              </tr>
            @endfor
            </tbody>
        </table>
    </div>
    `
)
<!-- END -->