<!-- BEGIN -->
@import gg.jte.Content
@import exercise.util.NamedRoutes
@import exercise.dto.BasePage

@param BasePage page = null
@param Content style = null
@param Content content

<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    @if(style != null)${style}@endif
    <title>Hello Hexlet!</title>
  </head>
  <body class="d-flex flex-column min-vh-100">
      <header class="mb-auto">
          <div class="expand navbar-expand" id="navbarsExample01" style>
              <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
                  <a class="navbar-brand" style="padding-right: 12px; padding-left: 12px"
                     href="${NamedRoutes.rootPath()}">Main
                  </a>
                  <div class="collapse navbar-collapse">
                      <ul class="navbar-nav mr-auto">
                          <li class="nav-item">
                              <a class="nav-link" href="${NamedRoutes.postsPath()}">Posts</a>
                          </li>
                      </ul>
                  </div>
              </nav>
          </div>
      </header>
      <main role="main" class="container wrapper flex-grow-1">
      @if(page != null && page.getFlash() != null)
      <div class="alert alert-${page.getFlashType().toString()}" role="alert">
        ${page.getFlash()}
      </div>
      @endif
          <div class="my-4">
              ${content}
          </div>
      </main>
      <footer class="modal-footer mt-auto">
          <div>
              <a class="nav-link" href="https://github.com/asidowner" target="_blank">@asidowner</a>
          </div>
      </footer>
  </body>
</html>
<!-- END -->