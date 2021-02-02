describe('Authentication tests', () => {
  let jwt;

  beforeEach(() => {
    cy.request('POST', '/authenticate', {login: "login", password: "spa"})
      .its('body')
      .then((body) => {
        jwt = body;
      })
  })

  it('Getting JWT', () => {
    cy.request('POST', '/authenticate', { login: "login", password: "spa" })
      .its('status')
      .should('be.ok')
  })

  it('Refresh JWT', () => {
  })

  it('Authentication and get information about yourself', () => {
    cy.request({
      method: 'GET',
      url: '/user/profile',
      headers: {
        'Authorization': 'Bearer ' + jwt
      }
    }).then((response) => {
      expect(response.body.login).equal('login')
      expect(response.body.name).equal('Jan')
      expect(response.body.lastName).equal('Kowalski')
    })

    cy.request({
      method: 'GET',
      url: '/user/profile',
      failOnStatusCode: false
    }).its('status')
      .should('equal', 401)
  })
})
