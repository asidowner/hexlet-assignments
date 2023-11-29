<!-- BEGIN -->
@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    title = @`User`,
    content = @`
    <section class="w-100 px-4 py-5" style="">
          <div class="row d-flex justify-content-center">
            <div class="col col-md-9 col-lg-7 col-xl-6">
              <div class="card" style="border-radius: 15px;">
                <div class="card-body p-4">
                  <div class="d-flex text-black">
                    <div class="flex-shrink-0">
                      <img src="https://www.onlygfx.com/wp-content/uploads/2021/12/rubber-duck-5370.png" alt="Generic placeholder image" class="img-fluid" style="width: 180px; border-radius: 10px;">
                    </div>
                    <div class="flex-grow-1 ms-3">
                      <h5 class="mb-1">${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h5>
                      <p class="mb-2 pb-1" style="color: #2b2a2a;">${page.getUser().getEmail()}</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
     </section>
    `
)
<!-- END -->
