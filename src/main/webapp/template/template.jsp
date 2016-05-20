<%--
  Created by IntelliJ IDEA.
  User: arifen
  Date: 5/13/16
  Time: 6:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
  <head>
    <link rel="stylesheet" href="resources/css/style.css" type="text/css" />
  </head>

  <body>
    <div class="header">
      <tiles:insertAttribute name="header" />
    </div>
    <div class="menu">
      <tiles:insertAttribute name="menu" />
    </div>
    <div class="body">
      <tiles:insertAttribute name="body" />
    </div>
    <div class="footer">
      <tiles:insertAttribute name="footer" />
    </div>
  </body>
</html>
