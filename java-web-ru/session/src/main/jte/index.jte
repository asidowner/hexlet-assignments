@import exercise.util.NamedRoutes
@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(
    content = @`
    <div class="mx-auto p-4 py-md-5">
    @if(page.getName() == null)
        <a class="btn btn-primary btn-sm" href="${NamedRoutes.buildSessionPath()}">Войти</a>
    @else
    <h3>Привет, ${String.valueOf(page.getName())}</h3>
    <form action="${NamedRoutes.logoutPath()}" method="post">
        <input type="submit" class="btn btn-primary btn-sm" value="Выйти" />
    </form>
    @endif
    </div>
    `
)
