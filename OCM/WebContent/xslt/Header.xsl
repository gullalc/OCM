<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- TODO: Auto-generated template -->
	<xsl:param name="acces"></xsl:param>
	<xsl:variable name="access" select="$acces"></xsl:variable>
	<xsl:template match="/">
		<xsl:variable name="usertype" select="/profile/usertype">

		</xsl:variable>
		<head>
			<link href='style/homepage.css' rel='stylesheet' type='text/css' />
		</head>
		<center>

			<div id="header1">
				<p class="lft">
					<xsl:call-template name="title"></xsl:call-template>
				</p>
				<div class="rgt">
							<ol>
								<li>
									<a href="/OCM">Home</a>
								</li>
								<li>
									<a>|</a>
								</li>
								<li>
									<a href="/OCM/FAQ.jsp">FAQ</a>
								</li>
								<li>
									<a>|</a>
								</li>
								<li>
									<a href="/OCM/complaint.jsp">Contact Us</a>
								</li>
							</ol>
						</div>

			</div>

			<div id="header2">
				<div id="search">
							<form action="http://localhost:8080/OCM/Search">
								<table width="181">


									<tr height="30px">
										<td>

											<div class="select">

												<select name="cat">
											<xsl:for-each
												select="document('http://localhost:8080/OCM/xml/mainpage.xml')//category">
												<option>
													<xsl:value-of select="."></xsl:value-of>
												</option>
											</xsl:for-each>
										</select>
											</div>
										</td>
										<td>
											<input id="txt" type="text" name="q"/>
										</td>
										<td>
											<input id="btn" type="Submit" value="" />
										</td>


									</tr>
								</table>
							</form>
							
				</div>

			</div>
			<div id="menu">
				<ol>
					<li>
						<a href="/OCM">HOME</a>
					</li>
					<li>
						<a href="/OCM">ARCHIVES</a>
					</li>
					<li>
						<a href="http://localhost:8080/OCM/gallery.jsp">PHOTO GALLERY</a>
					</li>
					<li>
						<a href="http://localhost:8080/OCM/threadcreate.jsp">DISCUSSION FORUM</a>
					</li>
					<li>
						<a href="http://localhost:8080/OCM/Editor'sDesk.jsp">EDITOR'S DESK</a>
					</li>
					<li>
						<a href="http://localhost:8080/OCM/Director.jsp">DIRECTOR'S DESK</a>
					</li>
					<li>
						<a>ABOUT US</a>
					</li>
				</ol>
			</div>
		</center>

	</xsl:template>
	<xsl:param name="user" select="//name"></xsl:param>
	<xsl:template name="title">
		Welcome
		<xsl:value-of select="$user"></xsl:value-of>
	</xsl:template>
</xsl:stylesheet>
