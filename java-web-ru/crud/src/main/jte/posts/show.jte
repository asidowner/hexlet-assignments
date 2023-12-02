@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
    content = @`
        <div class="container mt-5">
            <div class="row">
                <div class="col-lg-8">
                    <!-- Post content-->
                    <article>
                        <!-- Post header-->
                        <header class="mb-4">
                            <!-- Post title-->
                            <h1 class="fw-bolder mb-1">${page.getPost().getName()}</h1>
                        </header>
                        <!-- Post content-->
                        <section class="mb-5">
                            <p class="fs-5 mb-4">${page.getPost().getBody()}</p>
                        </section>
                    </article>
                    <!-- Return section-->
                    <section class="mb-5">
                        <a class="btn btn-primary btn-sm" href="${NamedRoutes.postsPath()}">Все посты</a>
                    </section>
                </div>
            </div>
        </div>
    `
)
