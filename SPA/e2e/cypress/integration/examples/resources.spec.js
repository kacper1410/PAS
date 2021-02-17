describe('Resources test', () => {
  let etag;

  let resource = {
    ISBN: '',
    title: '',
    author: '',
    available: true,
    resourceId: '',
    publishYear: 0
  }

  beforeEach(() => {
    cy.request({
      method: 'GET',
      url: '/resource/getAllBooks'
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

  beforeEach('Getting etag', () => {
    cy.request({
      method: 'GET',
      url: '/resource/getResourceById/' + resource.resourceId
    }).then((response) => {
      etag = response.headers.etag
      etag = etag.replace('\"', '')
      etag = etag.replace('\"', '')
    })
  })

  it('Create resource (positive)', () => {
    cy.request({
      method: 'POST',
      url: '/resource/addBook',
      body: resource
    }).then((response) => {
      expect(response.status).equal(204)
    })
  })

  it('Read resource (positive)', () => {
    cy.request({
      method: 'GET',
      url: '/resource/getResourceById/' + resource.resourceId
    }).then((response) => {
      expect(response.status).equal(200)
      expect(response.body.ISBN).equal(resource.ISBN)
      expect(response.body.title).equal(resource.title)
      expect(response.body.author).equal(resource.author)
      expect(response.body.available).equal(resource.available)
      expect(response.body.publishYear).equal(resource.publishYear)
    })
  })

  it('Update resource (positive)', () => {
    let editResource = {
      ISBN: 312321312312,
      title: 'ResourceTitle',
      author: 'ResourceAuthor',
      available: true,
      publishYear: 2000
    }

    if (resource.available) {
      cy.request({
        method: 'PUT',
        url: '/resource/updateBookById/' + resource.resourceId,
        body: editResource,
        headers: {
          'If-match': etag,
        }
      })

      cy.wait(500).then(() => {
        cy.request({
          method: 'GET',
          url: '/resource/getResourceById/' + resource.resourceId
        }).then((response) => {
          expect(response.body.title).equal(editResource.title)
          expect(response.body.author).equal(editResource.author)
          expect(response.body.publishYear).equal(editResource.publishYear)
        })
      })
    }
  })

  it('Delete resource (positive)', () => {
    let deleteResourceId;

    cy.request({
      method: 'GET',
      url: '/resource/getAllAvailableResources'
    }).its('body')
      .then((resources) => {
        deleteResourceId = resources[1].resourceId;
      })

    cy.wait(500).then(() => {
      cy.request({
        method: 'GET',
        url: '/resource/getResourceById/' + deleteResourceId
      })
    }).its('status')
      .should('equal', 200)

    cy.wait(500).then(() => {
      cy.request({
        method: 'DELETE',
        url: '/resource/removeResource/' + deleteResourceId
      }).its('status')
        .should('equal', 204)
    })

    cy.wait(500).then(() => {
      cy.request({
        method: 'GET',
        url: '/resource/getResourceById/' + deleteResourceId,
        failOnStatusCode: false
      }).its('status')
        .should('equal', 404)
    })

    cy.wait(500).then(() => {
      cy.request({
        method: 'POST',
        url: '/resource/addBook',
        body: resource
      }).then((response) => {
        expect(response.status).equal(204)
      })
    })
  })

  it('Authentication and borrow resource (positive)', () => {
    let jwt;

    cy.request('POST', '/authenticate', {login: "cli1", password: "spa"})
      .its('body')
      .then((body) => {
        jwt = body;
      })

    if (resource.available) {
      cy.wait(500).then(() => {
        cy.request({
          method: 'GET',
          url: '/resource/getResourceById/' + resource.resourceId
        }).then((response) => {
          expect(response.body.available).equal(true)
        })
      })

      cy.wait(500).then(() => {
        cy.request({
          method: 'POST',
          url: '/borrow/allocate/' + resource.resourceId,
          headers: {
            'Authorization': 'Bearer ' + jwt
          }
        }).its('status')
          .should('equals', 204)
      })
    } else {
      cy.wait(500).then(() => {
        cy.request({
          method: 'GET',
          url: '/resource/getResourceById/' + resource.resourceId
        }).then((response) => {
          expect(response.body.available).equal(false)
        })
      })
    }
  })

  it('Resource with negative length (negative)', () => {
    let editResource = {
      ISBN: 312321312312,
      title: 'ResourceTitle',
      author: 'ResourceAuthor',
      available: true,
      length: -2000
    }

    cy.request({
      method: 'PUT',
      url: '/resource/updateAudioBookById/' + resource.resourceId,
      body: editResource,
      failOnStatusCode: false,
      headers: {
        'If-match': etag,
      }
    }).its('status')
      .should('equal', 406)
  })

  it('Change resource id (negative)', () => {
    let editResource = {
      ISBN: 312321312312,
      title: 'ResourceTitle',
      author: 'ResourceAuthor',
      available: true,
      publishYear: 2000
    }

    cy.request({
      method: 'PUT',
      url: '/resource/updateBookById/' + resource.resourceId + 1,
      body: editResource,
      failOnStatusCode: false,
      headers: {
        'If-match': etag,
      }
    }).its('status')
      .should('equal', 412)
  })

  it('Resource actual borrowed (negative)', () => {
    let jwt;

    cy.request('POST', '/authenticate', {login: "cli1", password: "spa"})
      .its('body')
      .then((body) => {
        jwt = body;
      })

    if (!resource.available) {
      cy.wait(500).then(() => {
        cy.request({
          method: 'POST',
          url: '/borrow/allocate/' + resource.resourceId,
          failOnStatusCode: false,
          headers: {
            'Authorization': 'Bearer ' + jwt
          }
        }).its('status')
          .should('equals', 406)
      })
    }
  })
})
