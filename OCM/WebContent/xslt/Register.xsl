<?xml version="1.0" encoding="UTF-8"?>

<!-- New document created with EditiX at Thu Feb 23 14:55:25 IST 2012 -->

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" />

	<xsl:template match="/">
		<html>
			<body>
				<center>
					<table>
						<tr>
							<td colspan="2"></td>
						</tr>
						<xsl:apply-templates select="/root/fname"></xsl:apply-templates>
						<xsl:apply-templates select="/root/lname"></xsl:apply-templates>
						<xsl:apply-templates select="/root/occ"></xsl:apply-templates>
						<xsl:apply-templates select="/root/batch"></xsl:apply-templates>
						<xsl:apply-templates select="/root/cls"></xsl:apply-templates>
						<xsl:apply-templates select="/root/rno"></xsl:apply-templates>
						<xsl:apply-templates select="/root/adr"></xsl:apply-templates>
						<xsl:apply-templates select="/root/email"></xsl:apply-templates>
						<xsl:apply-templates select="/root/dob"></xsl:apply-templates>
						<xsl:call-template name="buttons"></xsl:call-template>



					</table>
					<div id="Reason">
					</div>
				</center>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="/root/fname">

		<tr>

			<td>First Name</td>

			<td>
				<xsl:value-of select=". " />
			</td>

		</tr>

	</xsl:template>

	<xsl:template match="/root/lname">

		<tr>

			<td>Last Name</td>

			<td>
				<xsl:value-of select=". " />
			</td>

		</tr>

	</xsl:template>

	<xsl:template match="/root/rno">

		<tr>

			<td>Roll Number</td>

			<td>
				<xsl:value-of select=". " />
			</td>

		</tr>

	</xsl:template>

	<xsl:template match="/root/gender">

		<tr>

			<td>Gender</td>

			<td>
				<xsl:value-of select=". " />
			</td>

		</tr>

	</xsl:template>

	<xsl:template match="/root/cls">

		<tr>

			<td>Class</td>

			<td>
				<xsl:value-of select=". " />
			</td>

		</tr>

	</xsl:template>

	<xsl:template match="/root/dob">

		<tr>

			<td>Date Of Birth</td>

			<td>
				<xsl:value-of select=". " />
			</td>

		</tr>

	</xsl:template>

	<xsl:template match="/root/occ">

		<tr>

			<td>Occupation</td>

			<td>
				<xsl:value-of select=". " />
			</td>

		</tr>

	</xsl:template>

	<xsl:template match="/root/email">

		<tr>

			<td>Email</td>

			<td>
				<xsl:value-of select=". " />
			</td>

		</tr>

	</xsl:template>

	<xsl:template match="/root/batch">

		<tr>

			<td>Batch</td>

			<td>
				<xsl:value-of select=". " />
			</td>

		</tr>

	</xsl:template>


	<xsl:template match="/root/adr">

		<tr>

			<td>Address</td>

			<td>
				<xsl:value-of select=". " />
			</td>

		</tr>

	</xsl:template>

	<xsl:param name="rid"></xsl:param>
	<xsl:template name="buttons">
		<tr>
			<td>
				<form action="http://localhost:8080/OCM/ProcessRegRequests"
					method="GET">
					<input type="hidden" name="rid" value="{$rid}" />
					<input type='submit' value='Accept' />
				</form>

			</td>
			<td>
				<form action="http://localhost:8080/OCM/ProcessRegRequests"
					method="POST">
					<input type="hidden" name="rid" value="{$rid}" />
					<input type='submit' value='Reject' />
				</form>
			</td>
		</tr>
	</xsl:template>

</xsl:stylesheet>

