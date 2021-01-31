describe('Authentication tests', () => {

  it('Getting JWT', () => {
    cy.request('POST', '/authenticate', { login: "login", password: "spa" })
      .its('status')
      .should('be.ok')
  })

  it('Refresh JWT', () => {
  })
})
