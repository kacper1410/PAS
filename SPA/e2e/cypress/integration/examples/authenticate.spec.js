/// <reference types="cypress" />

describe('Authentication tests', () => {
  it('Getting jwt', () => {
    cy.request('POST', '/authenticate', { login: "login", password: "spa" })
      .its('status')
      .should('be.ok')
  })
})
