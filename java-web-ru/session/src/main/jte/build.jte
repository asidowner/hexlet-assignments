@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
            @if(page.getError() != null)
                <div class="mb-3">
                    <ul>
                        @for(var validator : page.getErrors().values())
                            @for(var error : validator)
                                <li>${error.getMessage()}</li>
                            @endfor
                        @endfor
                    </ul>
                </div>
            @endif
            <form action="${NamedRoutes.loginPath()}" method="post">
                <div class="mb-3">
                    <label class="form-label">
                    Nickname
                    <input type="text" class="form-control" required name="name" value="page.getName()" />
                    </label>
                </div>
                <div class="mb-3">
                    <label class="form-label">
                    Пароль
                    <input type="password" class="form-control" required name="password" />
                    </label>
                </div>
                <input type="submit" class="btn btn-primary" value="Зарегистрировать" />
            </form>
        </div>
    `
)
