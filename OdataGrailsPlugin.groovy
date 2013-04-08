class OdataGrailsPlugin {
    def version = "0.3"
    def grailsVersion = "2.0 > *"
    def title = "Open Data Viewer"
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

    def documentation = "https://github.com/agitlin/odata/"

    def license = "APACHE"
    def developers = [
        [name: "Alex Gitlin", email: "speakers.meet@gmail.com"],
        [name: "Artem Portnoy", email: "artem.b.portnoy@gmail.com"]
    ]

    def issueManagement = [system: "JIRA", url: "https://github.com/agitlin/odata/issues"]
    def scm = [url: "https://github.com/agitlin/odata/"]
}
