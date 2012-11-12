<%@page pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
<title>SSA Bankfile Production</title>
  <style>
    .error {color: #ff0000;}
  </style>
</head>
<body link=white>
<h2><font color=#15317E></font></h2>

<TABLE align=left width=700 border=0 cellspacing=0 bgcolor=darkgrey frame=box>
  <TR height=250 bgcolor=#2B3856>
    <TD width=20%>&nbsp;</TD>
    <TD><h2><font color=white>OWASP </font>
             <font color=lightblue>Top </font>
             <font color=white>10 </font>
             <font color=lightblue>Demo</font>
    </TD>
  </TR>
  <TR height=50 bgcolor=#2B3856>
    <TD width=20%>&nbsp;</TD>
    <TD>
	<form:form commandName="login" method="POST">

        <font color=white>
        <I>Please log in:</I><br>
	<form:input path="username" size="15"/>
	<form:password path="password" size="15"/>
	<input type="submit" name="search" value="Let me in" style="background-color:darkgrey"/></br>
        <form:errors path="username" cssClass="error"/>
        <form:errors path="password" cssClass="error"/><br/>
        <p> Copyright 2012 &#169; accuitysolutions.com
        </font>

	</form:form>
    </TD>
  </TR>
  <TR height=250 bgcolor=#2B3856>
    <TD>&nbsp;</TD>
    <TD>&nbsp;</TD>
  </TR>
  <TR height=150>
    <TD bgcolor=#646D7E>&nbsp;</TD>
    <TD bgcolor=#646D7E>
        <font color=white>
              <br>t: +1 847 933 8162
              <br>f: +1 847 933 8101
              <p>Email: <a href="mailto:delai.huang@accuitysolutions.com?subject=SSABankPromo">
                 delai.huang@accuitysolutions.com</a>
        </font>
    </TD>
  </TR>
</TABLE>


