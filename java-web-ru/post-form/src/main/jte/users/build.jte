<!-- BEGIN -->
@template.layout.page(
    content = @`
    <div class="form-signup">
        <form action="/users" method="POST">
            <h1 class="h3 mb-3 fw-normal">Please sign up</h1>

            <div class="form-floating">
              <input type="firstName" name="firstName" required class="form-control" id="floatingFirstName" placeholder="Tony">
              <label for="floatingFirstName">First name</label>
            </div>
            <div class="form-floating">
              <input type="lastName" name="lastName" required class="form-control" id="floatingLastName" placeholder="Makarony">
              <label for="floatingLastName">Last name</label>
            </div>
            <div class="form-floating">
              <input type="email" name="email" required class="form-control" id="floatingEmail" placeholder="name@example.com">
              <label for="floatingEmail">Email address</label>
            </div>
            <div class="form-floating">
              <input type="password" name="password" required class="form-control" id="floatingPassword" placeholder="Password">
              <label for="floatingPassword">Password</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary" type="submit">Sign up</button>
          </form>
    </div>
    `,
    style=@`
    <style type="text/css">
        .form-signup {
          width: 100%;
          max-width: 330px;
          padding: 15px;
          margin: auto;
        }
        
        .form-signup .checkbox {
          font-weight: 400;
        }
        
        .form-signup .form-floating:focus-within {
          z-index: 2;
        }

        .form-signup input[type="firstName"] {
          margin-bottom: -1px;
          border-bottom-right-radius: 0;
          border-bottom-left-radius: 0;
        }

        .form-signup input[type="lastName"] {
          margin-bottom: -1px;
          border-bottom-right-radius: 0;
          border-bottom-left-radius: 0;
        }
        
        .form-signup input[type="email"] {
          margin-bottom: -1px;
          border-bottom-right-radius: 0;
          border-bottom-left-radius: 0;
        }
        
        .form-signup input[type="password"] {
          margin-bottom: 10px;
          border-top-left-radius: 0;
          border-top-right-radius: 0;
        }
    </style>
    `
)
<!-- END -->
