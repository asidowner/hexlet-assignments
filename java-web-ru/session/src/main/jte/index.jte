@import exercise.util.NamedRoutes
@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(
    content = @`
    @if(page.getName() == null)
        <a class="btn btn-primary btn-sm" href="${NamedRoutes.buildSessionPath()}">Войти</a>
    @else
        <a class="btn btn-primary btn-sm" href="${NamedRoutes.logoutPath()}">Выйти</a>
    @endif
    `
)
