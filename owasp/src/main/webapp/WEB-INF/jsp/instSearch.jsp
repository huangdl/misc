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
			<font color=darkblue>Institution Search Form</font>
		</h2>

		<TABLE width=650 align="center">
			<tr bgcolor=darkblue>
				<td colspan=4></td>
			</tr>
			<tr>
				<td solspan=4>&nbsp;</td>
			</tr>

			<form:form commandName="inst" method="POST">

				<tr>
					<td align=right>Institution Id:</td>
					<td><form:input path="id" /> <form:errors path="id"
							cssClass="error" /><br /></td>
					<td align=right>Legal Title:</td>
					<td><form:input path="title" /> <form:errors path="title"
							cssClass="error" /><br /></td>
				</tr>
				<tr>
					<td align=right>Status:</td>
					<td><form:select path="status" cssStyle="width:100">
							<form:option value="" label="--- Select ---" />
							<form:options items="${statusList}" />
						</form:select>
					</td>
					<td align=right>General Category:</td>
					<td><form:select path="category" cssStyle="width:100">
							<form:option value="" label="--- Select ---" />
							<form:options items="${categoryList}" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td align=right>Country:</td>
					<td><form:select path="country" cssStyle="width:100">
							<form:option value="" label="--- Select ---" />
							<form:options items="${countryList}" />
						</form:select>
					</td>
					<td align=right>Borough:</td>
					<td><form:select path="borough" cssStyle="width:100">
							<form:option value="0" label="--- Select ---" />
							<form:options items="${boroughList}" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td align=right>State/Province:</td>
					<td><form:select path="state" cssStyle="width:100">
							<form:option value="" label="--- Select ---" />
							<form:options items="${stateList}" />
						</form:select>
					</td>
					<td align=right>Island:</td>
					<td><form:select path="island" cssStyle="width:100">
							<form:option value="" label="--- Select ---" />
							<form:options items="${islandList}" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td align=right>City:</td>
					<td><form:input path="city" /> <form:errors path="city"
							cssClass="error" /><br /></td>
					<td align=right>Village:</td>
					<td><form:input path="village" /> <form:errors path="village"
							cssClass="error" /><br /></td>
				</tr>
				<tr>
					<td align=right>Zip:</td>
					<td><form:input path="zip" /> <form:errors path="zip"
							cssClass="error" /><br /></td>
					<td align=right>Max Return:</td>
					<td><form:input path="max" /> <form:errors path="max"
							cssClass="error" /><br /></td>
				</tr>
				<tr>
					<td align=right>Sort:</td>
					<td><form:radiobutton path="sort" value="asc" /> Asc <form:radiobutton
							path="sort" value="des" /> Desc</td>
					<td align=left><input type="submit" name="search"
						value="Search" /> <input type="reset" name="reset" value="Reset" />
					</td>
					<td colspan=1></td>
				</tr>

			</form:form>

		</TABLE>

		<P>

			<c:if test="${not empty requestScope.instList}">
				<table width=650 padding=0>
					<tr>
						<th align=left colspan=7>Search Result</th>
					</tr>
					<tr bgcolor=lightgrey>
						<td>Inst Id
						</th>
						<td>Status
						</th>
						<td>Category
						</th>
						<td>Title
						</th>
						<td>City
						</th>
						<td>State
						</th>
						<td>Country
						</th>
					</tr>
					<c:forEach var="ins" items="${requestScope.instList}"
						varStatus="rowCounter">
						<c:choose>
							<c:when test="${rowCounter.count % 2 == 0}">
								<tr bgcolor=lightgrey>
									<td><a href=instDetail.htm?instid=${ins.id}
										onclick="return popitup('instDetail.htm?instid=${ins.id}')">${ins.id}</a>
									</td>
									<td>${ins.status}</td>
									<td>${ins.category}</td>
									<td>${ins.title}</td>
									<td>${ins.city}</td>
									<td>${ins.state}</td>
									<td>${ins.country}</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td><a href=instDetail.htm?instid=${ins.id}
										onclick="return popitup('instDetail.htm?instid=${ins.id}')">${ins.id}</a>
									</td>
									<td>${ins.status}</td>
									<td>${ins.category}</td>
									<td>${ins.title}</td>
									<td>${ins.city}</td>
									<td>${ins.state}</td>
									<td>${ins.country}</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</table>
			</c:if>
</body>
</html>



