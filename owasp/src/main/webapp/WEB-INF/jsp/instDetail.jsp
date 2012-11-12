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
</head>
<body>

	<center>
		<TABLE width=650 align="center" frame=box bgcolor=darkgrey>
					<tr align="center" height="1">
						<td>Loc |</td>
						<td>HO |</td>
						<td>RTN |</td>
						<td>Service |</td>
						<td>Subcategory |</td>
						<td>Title |</td>
						<td>Contact |</td>
						<td>Inst Owned |</td>
						<td>History |</td>
						<td>Advertising</td>
					</tr>
		</TABLE>
		<h2>
			<font color=darkblue>Institution Detail Form</font>
		</h2>

		<TABLE width=650 align="center">
			<tr bgcolor=darkblue><td colspan=4></td></tr>
			<tr>
				<td colspan=4 align=center>${instDetail.title}</td>
			</tr>
		</TABLE>
		<P>
		<TABLE width=650 align="center" frame=box bgcolor=lightgrey>
			<tr>
				<td colspan=4 align=center><font color=red>${message}</font>
				</td>
			</tr>
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>

			<form:form commandName="instDetail" method="POST">
				<tr>
					<td align=right>Institution Id:</td>
					<td colspan=3><form:input path="id" readonly="true" size="10" />
						<form:errors path="id" cssClass="error" /> Legal Title: <form:input
							path="title" size="40" /> <form:errors path="title"
							cssClass="error" />
					</td>
				</tr>

				<tr>
					<td align=right>Institution Status:</td>
					<td><form:select path="status" cssStyle="width:100" disabled="${instDetail.readonly}">
							<form:options items="${statusList}" />
						</form:select></td>
					<td align=right>Year Established:</td>
					<td><form:input path="yearEstablished" size="10" /> <form:errors
							path="yearEstablished" cssClass="error" /></td>
				</tr>

				<tr>
					<td align=right>Organization Type:</td>
					<td><form:select path="organizationTypeCode"
							cssStyle="width:100">
							<form:option value="${instDetail.organizationTypeCode}"
								label="${instDetail.organizationTypeDesc}" />
							<form:options items="${organizationTypeList}" />
						</form:select></td>
					<td align=right>Employer Id:</td>
					<td><form:input path="employerId" size="10" /> <form:errors
							path="employerId" cssClass="error" /></td>
				</tr>


				<tr>
					<td align=right>Lead Institution:</td>
					<td><form:select path="leadInstitution" cssStyle="width:100">
							<form:options items="${yesNoList}" />
						</form:select></td>
					<td align=right>Number Employees:</td>
					<td><form:input path="numberEmployees" size="10" /> <form:errors
							path="numberEmployees" cssClass="error" /></td>
				</tr>

				<tr>
					<td align=right>Authority Charter:</td>
					<td><form:select path="authorityCharter" cssStyle="width:100">
							<form:option value="${instDetail.authorityCharter}" />
							<form:options items="${authorityCharterList}" />
						</form:select></td>
					<td align=right>Number Members:</td>
					<td><form:input path="numberMembers" readonly="${instDetail.readonly}" size="10" />
						<form:errors path="numberMembers" cssClass="error" /></td>
				</tr>

				<tr>
					<td align=right>Trust Powers:</td>
					<td><form:select path="trustPowers" cssStyle="width:100">
							<form:options items="${trustPowerList}" />
						</form:select></td>
					<td align=right>Member Banks:</td>
					<td><form:input path="memberBanks" readonly="${instDetail.readonly}" size="10" />
						<form:errors path="memberBanks" cssClass="error" /></td>
				</tr>

				<tr>
					<td align=right>Insurance Type:</td>
					<td><form:select path="insuranceTypeCode" cssStyle="width:100">
							<form:option value="${instDetail.insuranceTypeCode}"
								label="${instDetail.insuranceTypeCode}" />
							<form:options items="${insuranceTypeList}" />
						</form:select>
					<td align=right>Total Branches:</td>
					<td><form:input path="totalBranches" size="10" /> <form:errors
							path="totalBranches" cssClass="error" /></td>
					</td>

				</tr>

				<tr>
					<td align=right>General Category:</td>
					<td><form:select path="generalCategoryCode"
							cssStyle="width:100">
							<form:option value="${instDetail.generalCategoryCode}"
								label=" ${instDetail.generalCategoryDesc}" />
							<form:options items="${generalCategoryList}" />
						</form:select></td>
					<td align=right>Foreign Locations:</td>
					<td><form:input path="foreignLocations" size="10" /> <form:errors
							path="foreignLocations" cssClass="error" /></td>
				</tr>

				<tr>
					<td align=right>Subcategory:</td>
					<td><form:select path="subcategoryCode" cssStyle="width:100">
							<form:option value="${instDetail.subcategoryCode}"
								label=" ${instDetail.subcategoryDesc}" />
							<form:options items="${subcategoryList}" />
						</form:select></td>
					<td align=right>Cu Share Draft Flag:</td>
					<td><form:input path="cuShareDraftFlag" size="10" /> <form:errors
							path="cuShareDraftFlag" cssClass="error" /></td>
				</tr>

				<tr>
					<td align=right>Min. Acct Size($):</td>
					<td><form:input path="minAcct" size="10" /> <form:errors
							path="minAcct" cssClass="error" /></td>
					<td align=right>Admin Employees:</td>
					<td><form:input path="adminEmp" size="10" /> <form:errors
							path="adminEmp" cssClass="error" /></td>
				</tr>

				<tr>
					<td align=right></td>
					<td></td>
					<td align=right>Prof Employees:</td>
					<td><form:input path="profEmp" size="10" /> <form:errors
							path="profEmp" cssClass="error" /></td>
				</tr>

				<tr>
					<td colspan=2 align=right><input type="submit" name="save"
						value="Save" /></td>
					<td colspan=2 align=left><input type="reset" name="reset"
						value="Reset" /></td>
				</tr>

			</form:form>

		</TABLE>

</body>
</html>



