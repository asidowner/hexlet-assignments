<!-- BEGIN -->
@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    title = @`Users list`,
    content = @`
    <div class="table-responsive">
        <table class="table table-striped table-sm">
                  <thead>
                    <tr>
                      <th scope="col">#</th>
                      <th scope="col">Full Name</th>
                      <th scope="col">Email</th>
                    </tr>
                  </thead>
                  <tbody>
                  @for(var item : page.getUsers())
                    <tr>
                      <td><a href="/users/${item.getId()}">${item.getId()}</a></td>
                      <td>${item.getFirstName()} ${item.getLastName()}</td>
                      <td>${item.getEmail()}</td>
                    </tr>
                  @endfor
                  </tbody>
        </table>
    </div>
    `
)
<!-- END -->
