describe('Users test', () => {

  let jwt;
  let etag;

  let user = {
    userId: '',
    name: '',
    login: '',
    lastName: ''
  }

  beforeEach('Getting jwt', () => {
    cy.request('POST', '/authenticate', { login: "login", password: "spa" })
      .its('body')
      .then((body) => {
        jwt = body;
      })
  })

  beforeEach('Getting first active client', () => {
    cy.request({
      method: 'GET',
      url: '/user/getAllActiveClients',
      headers: {
        'Authorization': 'Bearer ' + jwt
      }
    }).its('body')
      .then((users) => {
        user.userId = users[0].userId;
        user.name = users[0].name;
        user.login = users[0].login;
        user.lastName = users[0].lastName;
      })
  })

  beforeEach('Getting etag', () => {
    cy.request({
      method: 'GET',
      url: '/user/getUserById/' + user.userId
    }).then((response) => {
      etag = response.headers.etag
      etag = etag.replace('\"', '')
      etag = etag.replace('\"', '')
    })
  })

  it('Create user', () => {
    let user = {
      login: 'test-client' + Math.random() * 10000,
      name: 'test',
      lastName: 'client',
      age: 20
    }

    cy.request({
      method: 'POST',
      url: '/user/addClient',
      body: user,
      headers: {
        'Authorization': 'Bearer ' + jwt
      }
    }).then((response) => {
      expect(response.status).equal(204)
    })

    cy.request({
      method: 'GET',
      url: '/user/getUserByLogin/' + user.login,
      headers: {
        'Authorization': 'Bearer ' + jwt
      }
    }).then((response) => {
      expect(response.status).equal(200)
      expect(response.body.login).equal(user.login)
      expect(response.body.name).equal(user.name)
      expect(response.body.lastName).equal(user.lastName)
      expect(response.body.age).equal(user.age)
    })
  })

  it('Read user', () => {
    cy.request({
      method: 'GET',
      url: '/user/getUserById/' + user.userId
    }).then((response) => {
      expect(response.status).equal(200)
      expect(response.body.name).equal(user.name)
      expect(response.body.login).equal(user.login)
      expect(response.body.lastName).equal(user.lastName)
    })
  })

  it('Update user', () => {
    let editUser = {
      name: 'testNameEEE',
      lastName: 'testLastName',
      age: 20,
    }

    cy.request({
      method: 'PUT',
      url: '/user/updateClient/' + user.userId,
      body: editUser,
      headers: {
        'If-match': etag,
      }
    })

    cy.wait(500).then(() => {
      cy.request({
        method: 'GET',
        url: '/user/getUserById/' + user.userId
      }).then((response) => {
        expect(response.body.name).equal(editUser.name)
        expect(response.body.login).equal(user.login)
        expect(response.body.lastName).equal(editUser.lastName)
        expect(response.body.age).equal(editUser.age)
      })
    })
  })

  it('Client with not valid age', () => {
    let editUser = {
      name: 'testNameEEE',
      lastName: 'testLastName',
      age: -20,
    }

    cy.request({
      method: 'PUT',
      url: '/user/updateClient/' + user.userId,
      body: editUser,
      failOnStatusCode: false,
      headers: {
        'If-match': etag,
      }
    }).its('status')
      .should('equal', 406)
  })

  it('Authentication and get information about yourself', () => {
  })
})
