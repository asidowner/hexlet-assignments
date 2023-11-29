<!-- BEGIN -->
@import exercise.dto.users.UsersPage
@param UsersPage page

@template.template(
    title = @`Users list`,
    content = @`
    <h2>${page.getHeader()}</h2>
    <div class="table-responsive">
        <table class="table table-striped table-sm">
                  <thead>
                    <tr>
                      <th scope="col">#</th>
                      <th scope="col">First Name</th>
                      <th scope="col">Last Name</th>
                      <th scope="col">Email</th>
                    </tr>
                  </thead>
                  <tbody>
                  @for(var item : page.getUsers())
                    <tr>
                      <td><a href="/users/${item.getId()}">${item.getId()}</a></td>
                      <td>${item.getFirstName()}</td>
                      <td>${item.getLastName()}</td>
                      <td>${item.getEmail()}</td>
                    </tr>
                  @endfor
                  </tbody>
        </table>
    </div>
    `
)
<!-- END -->
