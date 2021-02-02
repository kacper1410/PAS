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

  it('Wrong protocol (required HTTPS)', () => {
    cy.request({
      method: 'GET',
      url: 'http://localhost:8080/PAS/api',
      failOnStatusCode: false
    }).its('status')
      .should('equal', 404)
  })

  it('No JWT token', () => {
    cy.request({
      method: 'POST',
      url: '/borrow/allocate',
      failOnStatusCode: false
    }).its('status')
      .should('equal', 401)
  })

  it('Refresh JWT', () => {
    cy.wait(1000).then(() => {
      cy.request({
        method: 'GET',
        url: '/authenticate/refreshJWT',
        headers: {
          'Authorization': 'Bearer ' + jwt
        }
      }).then((response) => {
        expect(response.status).equal(202)
        let tmp = false;

        if (response.body === jwt) {
          tmp = true;
        }

        expect(tmp).equal(false)
      })
    })
  })

  it('Refresh JWT in inactive account', () => {
    let etag;
    let userId;

    cy.request('POST', '/authenticate', {login: "cli1", password: "spa"})
      .its('body')
      .then((body) => {
        jwt = body;
      })

    cy.request({
      method: 'GET',
      url: '/user/getUserByLogin/' + 'cli1',
      headers: {
        'Authorization': 'Bearer ' + jwt
      }
    }).then((response) => {
      etag = response.headers.etag
      userId = response.body.userId
      etag = etag.replace('\"', '')
      etag = etag.replace('\"', '')
    })

    cy.wait(500).then(() => {
      cy.request({
        method: 'PUT',
        url: '/user/deactivate/' + userId,
        headers: {
          'If-match': etag
        }
      }).its('status')
        .should('equal', 204)
    })

    cy.wait(1000).then(() => {
      cy.request({
        method: 'GET',
        url: '/authenticate/refreshJWT',
        headers: {
          'Authorization': 'Bearer ' + jwt
        },
        failOnStatusCode: false
      }).its('status')
        .should('equal', 401)
    })

    cy.wait(1500).then(() => {
      cy.request({
        method: 'PUT',
        url: '/user/activate/' + userId,
        headers: {
          'If-match': etag
        }
      }).its('status')
        .should('equal', 204)
    })
  })
})
