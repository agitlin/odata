class OdataGrailsPlugin {
    // the plugin version
    def version = "0.3"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.0 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Open Data Viewer" // Headline display name of the plugin
    def author = "Alex Gitlin, Artem Portnoy"
    def authorEmail = "speakers.meet@gmail.com"
    def description = '''\
Grails Plugin for building Open Data Protocol-based table views on top of Twitter Bootstrap.
Kickstart-with-bootstrap plugin installation is a prerequisite for installing his plugin. 

This plugin lets you create metadata-driven table views of OData sources. See the sample Netflix data table that comes with the code.  

Installation: 
1) install kickstart-with-bootstrap plugin
2) run kickstart script
3) install odata plugin
4) (optional) add the following line to the init closure of your BootStrap.groovy: 
org.grails.plugin.odata.Table.addSamples()
This should add two sample tables to your application. Use the supplied Table and Column controllers to change the views.

'''

    // URL to the plugin's documentation
    def documentation = "https://github.com/agitlin/odata/"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
    def developers = [ [ name: "Alex Gitlin", email: "speakers.meet@gmail.com" ],
						[ name: "Artem Portnoy", email: "artem.b.portnoy@gmail.com" ]
						]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "JIRA", url: "https://github.com/agitlin/odata/issues" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/agitlin/odata/" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
