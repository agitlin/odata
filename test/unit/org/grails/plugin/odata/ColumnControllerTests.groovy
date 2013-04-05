package org.grails.plugin.odata



import org.junit.*
import grails.test.mixin.*

/**
 * ColumnControllerTests
 * A unit test class is used to test individual methods or blocks of code without considering the surrounding infrastructure
 */
@TestFor(ColumnController)
@Mock(Column)
class ColumnControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/column/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.columnInstanceList.size() == 0
        assert model.columnInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.columnInstance != null
    }

    void testSave() {
        controller.save()

        assert model.columnInstance != null
        assert view == '/column/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/column/show/1'
        assert controller.flash.message != null
        assert Column.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/column/list'


        populateValidParams(params)
        def column = new Column(params)

        assert column.save() != null

        params.id = column.id

        def model = controller.show()

        assert model.columnInstance == column
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/column/list'


        populateValidParams(params)
        def column = new Column(params)

        assert column.save() != null

        params.id = column.id

        def model = controller.edit()

        assert model.columnInstance == column
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/column/list'

        response.reset()


        populateValidParams(params)
        def column = new Column(params)

        assert column.save() != null

        // test invalid parameters in update
        params.id = column.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/column/edit"
        assert model.columnInstance != null

        column.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/column/show/$column.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        column.clearErrors()

        populateValidParams(params)
        params.id = column.id
        params.version = -1
        controller.update()

        assert view == "/column/edit"
        assert model.columnInstance != null
        assert model.columnInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/column/list'

        response.reset()

        populateValidParams(params)
        def column = new Column(params)

        assert column.save() != null
        assert Column.count() == 1

        params.id = column.id

        controller.delete()

        assert Column.count() == 0
        assert Column.get(column.id) == null
        assert response.redirectedUrl == '/column/list'
    }
}
