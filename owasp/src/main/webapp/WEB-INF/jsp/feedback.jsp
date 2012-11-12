<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<title>Data Entry System</title>

<style>
.error {
	color: #ff0000;
	font-weight: bold;
}
</style>

<script language="javascript" type="text/javascript">
	function popitup(url) {
		newwindow = window.open(url, "name", "height=600,width=850");
		if (window.focus) {
			newwindow.focus()
		}
		return false;
	}
</script>

</head>
<body>
	<p>
	<center>
		<br>
		<h2>
			<font color=darkblue>Feedback Form</font>
		</h2>

		<TABLE width=650 align="center">
			<tr bgcolor=darkblue>
				<td></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>

			<form:form commandName="feedback" method="POST">
				<tr>
					<td>Title:
						<form:input path="name" /> 
					</td>
				</tr>
				<tr>
					<td align="left">Comment:</td>
				</tr>
				<tr>
					<td >
						<form:textarea path="value" rows="5" cols="50" /> 
					</td>
				</tr>
				<tr>
					<td align="left">
						<input type="submit" name="submit" value="View/Post" />
					</td>
				</tr>
			</form:form>

		</TABLE>

		<P>

			<c:if test="${not empty requestScope.feedbackList}">
				<table width=650 padding=0>
					<tr>
						<th align=left colspan=7>Comments</th>
					</tr>
					<tr bgcolor=lightgrey>
						<th>Title</th>
						<th>comment</th>
					</tr>
					<c:forEach var="feedback" items="${requestScope.feedbackList}"
						varStatus="rowCounter">
						<c:choose>
							<c:when test="${rowCounter.count % 2 == 0}">
								<tr bgcolor=lightgrey>
									<td width="15%">${feedback.name}</td>
									<td>${feedback.value}</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td>${feedback.name}</td>
									<td>${feedback.value}</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</table>
			</c:if>
</body>
</html>



