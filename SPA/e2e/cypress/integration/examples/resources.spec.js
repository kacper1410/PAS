describe('Resources test', () => {
  let jwt;

  let resource = {
    ISBN: '',
    title: '',
    author: '',
    available: '',
    resourceId: '',
    publishYear: ''
  }

  beforeEach(() => {
    cy.request('POST', '/authenticate', {login: "login", password: "spa"})
      .its('body')
      .then((body) => {
        jwt = body;
      })
  })

  beforeEach(() => {
    cy.request({
      method: 'GET',
      url: '/resource/getAllAvailableResources',
      headers: {
        'Authorization': 'Bearer ' + jwt
      }
    }).its('body')
      .then((resources) => {
        resource.resourceId = resources[0].resourceId;
        resource.ISBN = resources[0].ISBN;
        resource.title = resources[0].title;
        resource.author = resources[0].author;
        resource.available = resources[0].available;
        resource.publishYear = resources[0].publishYear;
      })
  })

  it('Create resource', () => {
    cy.request({
      method: 'GET',
      url: '/resource/getResourceById/' + resource.resourceId,
      headers: {
        'Authorization': 'Bearer ' + jwt
      }
    }).then((response) => {
      expect(response.status).equal(200)
      expect(response.body.ISBN).equal(resource.ISBN)
      expect(response.body.title).equal(resource.title)
      expect(response.body.author).equal(resource.author)
      expect(response.body.available).equal(resource.available)
      expect(response.body.available).equal(resource.available)
      expect(response.body.publishYear).equal(resource.publishYear)
    })
  })

  it('Read resource', () => {
  })

  it('Update resource', () => {
  })

  it('Delete resource', () => {
  })

  it('Authentication and borrow resource', () => {
  })
})
