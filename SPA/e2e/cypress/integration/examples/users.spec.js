
describe('Users test', () => {
  let jwt;

  beforeEach(() => cy.request('POST', '/authenticate', { login: "login", password: "spa" })
    .its('body')
    .then((body) => {
      jwt = body;
    }))
})
