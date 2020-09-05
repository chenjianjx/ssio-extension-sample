# ssio-extension-sample
A sample of extending [ssio](https://github.com/chenjianjx/ssio) to support another file type

The file type here is "Base64CSV", a csv file where all the cells are Base64 encoded.

The code here is by no means the simplest solution to support "Base64CSV", but it shows you which files you should create to make a ssio extension.


## Code Directory Structure

* spi/clientexternal - The new param classes
* spi/internal - The implementation of your extension
  * ./model - new data structure that represents workbooks, sheets, rows and cells for the new file type
  * ./cellvaluebinder  - mapping property values and cell values
  * ./factory - The factory to create your workbook. This is the entry of the extension
* app/TrySsioBase64CsvExt.java  - It shows how to register your extension into ssio, and then run it  