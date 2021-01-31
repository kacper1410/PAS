describe('Response code test', () => {

  it('Client with not valid age', () => {
  })

  it('Resource with negative length', () => {
  })

  it('Wrong credentials', () => {
    cy.request({
      method: 'POST',
      url: '/authenticate',
      body: { login: "cli3", password: "spa" },
      failOnStatusCode: false
    }).its('status')
      .should('equal', 401)
  })

  it('Inactive account', () => {
      cy.request({
        method: 'POST',
        url: '/authenticate',
        body: { login: "cli3", password: "spa" },
        failOnStatusCode: false
      }).its('status')
        .should('equal', 401)
  })

  it('Resource actual borrowed', () => {
  })

  it('Wrong protocol (required HTTPS)', () => {
  })

  it('No JWT token', () => {
    cy.request({
      method: 'GET',
      url: '/authenticate',
      failOnStatusCode: false
    }).its('status')
      .should('equal', 405)

      // Add profile and allocate
  })

  it('Wrong user role', () => {
  })

  it('Change user id', () => {
  })

  it('Change resource id', () => {
  })

  it('Refresh JWT in inactive account', () => {
  })
})
