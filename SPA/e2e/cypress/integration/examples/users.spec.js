describe('Users test', () => {
  let jwt;

  let userId;
  let name;
  let login;
  let lastName;

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
      url: '/user/getAllActiveClients',
      headers: {
        'Authorization': 'Bearer ' + jwt
      }
    }).its('body')
      .then((users) => {
        userId = users[0].userId;
        name = users[0].name;
        login = users[0].login;
        lastName = users[0].lastName;
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
      url: '/user/getUserById/' + userId,
      headers: {
        'Authorization': 'Bearer ' + jwt
      }
    }).then((response) => {
      expect(response.status).equal(200)
      expect(response.body.name).equal(name)
      expect(response.body.login).equal(login)
      expect(response.body.lastName).equal(lastName)
    })
  })

  it('Update user', () => {
  })

  it('Authentication and get information about yourself', () => {
  })
})
