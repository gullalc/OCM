<?xml version="1.0" encoding="UTF-8"?>


<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" />
	<xsl:param name="acces"></xsl:param>
	<xsl:param name="userid"></xsl:param>
	<xsl:variable name="access" select="$acces"></xsl:variable>
	<xsl:template match="/">
		<xsl:variable name="usertype" select="/profile/usertype">

		</xsl:variable>
		<html>
			<head>
				<title>
					<xsl:call-template name="title"></xsl:call-template>
				</title>
				<link href="style/Profile.css" rel="stylesheet" type="text/css" />
				<script src="scripts/profile.js" type="text/javascript"></script>
				
			</head>
			<body>
				<center>

					<input type="hidden" id="uid" value="{$userid}"></input>
					<script type="text/javascript">tab('info');</script>
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
											<input id="txt" type="text" />
										</td>
										<td>
											<input id="Submit" type="Submit" value="" />
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
					<div id="main">
						<div id="left_content2">
							<div id="inf">
								<ul id="photo">
									<xsl:call-template name="img"></xsl:call-template>
								</ul>
								<hr />
								<ul id="view">
									<ul>
										<li class="title">Status</li>
										<li>
											<div id="status">
												<xsl:apply-templates select="/profile/status"></xsl:apply-templates>
											</div>
										</li>
										<li class="title">View</li>
										<li>
											<ul>
												<li onclick="tab('info')">Information</li>
												<li onclick="tab('articles')">Articles</li>
												<li onclick="tab('photos')">Photos</li>
												
												<li onclick="window.location.href='http://localhost:8080/OCM/DiscussionForum.jsp'">Threads</li>
											</ul>
										</li>
									</ul>
								</ul>
								<xsl:choose>
									<xsl:when test="($access='restricted')">
										<ul id="postnew">
											<ul>
												<li class="title">Post New</li>
												<li>
													<ul>
														<li onclick="tab('new_articles')">Articles</li>
														<li onclick="tab('new_photos')">Photos</li>
														<li
															onclick="window.location.href='http://localhost:8080/OCM/DiscussionForum.jsp'">Threads</li>
													</ul>
												</li>
											</ul>
										</ul>
										<ul id="update">
											<ul>
												<li class="title">Update</li>
												<li>
													<ul>
														<li onclick="tab('update_status')">Status</li>
														<li onclick="tab('update_dp')">Display Picture</li>
														
													</ul>
												</li>
											</ul>
										</ul>
										
										<xsl:if test="not($usertype='Admin')">
										<ul id="Messages">
											<ul>
												<li class="title">Send a message to:</li>
												<li>
													<ul>
														
														<li onclick="tab('contact_others')">Message</li>
													</ul>
												</li>
											</ul>
											
										</ul>
										</xsl:if>
										<ul id="Inbox">
											<ul>
												<li class="title">Inbox</li>
												<li>
													<ul>
														<li onclick="tab('inbox')">Inbox</li>

													</ul>
												</li>
											</ul>
										</ul>
										<xsl:if test="($usertype='Moderator')">
											<ul id="Mod_privileges">
												<ul>
													<li class="title">Moderator Privileges</li>
													<li>
														<ul>
															<li onclick="tab('postreq')">View Post Requests</li>
															<li onclick="tab('genrep')">Generate reports</li>

														</ul>
													</li>
												</ul>
											</ul>
										</xsl:if>
										<xsl:if test="($usertype='Admin')">
											<ul id="admin_privileges">
												<ul>
													<li class="title">Admin Privileges</li>
													<li>
														<ul>
															<li onclick="tab('postreq')">View Post Requests</li>
															<li onclick="tab('viewrep')">View reports</li>
															
															<li onclick="tab('tod')">Thought of the day</li>
															<li onclick="tab('wod')">Word of the day</li>
															<li onclick="tab('regreq')">Registeration requests</li>
															<li onclick="tab('complaints')">Complaints</li>
															<li onclick="tab('delusr')">Delete Users</li>
															
															<li onclick="tab('postnews')">Post News</li>
															<li onclick="tab('ep')">Editor's Pick</li>
															

														</ul>
													</li>
												</ul>
											</ul>
										</xsl:if>
										
									</xsl:when>
									

								</xsl:choose>
							</div>
						</div>
						<div id="right_content2">

							<div id="left_area">
							</div>


							<div id="right_area">
								<div id="progress">
									
									<xsl:choose>
										<xsl:when test="($usertype='Admin')">
										</xsl:when>
										<xsl:when test="$usertype='Moderator'">
										</xsl:when>
										<xsl:otherwise >
											<!--  <xsl:apply-templates select="/profile/progress"></xsl:apply-templates>-->
											<xsl:if test="($access='restricted')and ($usertype='Registered')">
											<div id="pr">
											<button type="button" onclick="tab('promote')">Promotion Request</button>
											</div>
											</xsl:if>
										</xsl:otherwise>
									</xsl:choose>
								</div>
							</div>


						</div>

					</div>
				</center>
			</body>
		</html>
	</xsl:template>
	<xsl:template name="img">
		<xsl:choose>
			<xsl:when test="/profile/image!=''">
			<xsl:variable name="im">
				<xsl:value-of select="/profile/image"></xsl:value-of>
			</xsl:variable>
				<img id="display_pic" src="{$im}" />
			</xsl:when>
			<xsl:otherwise>
				<img id="display_pic" src="style/images/Default.jpg" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="/profile/status">
		<center>
			<p>
				<xsl:value-of select="."></xsl:value-of>
			</p>
		</center>
	</xsl:template>
	<xsl:param name="user" select="//name"></xsl:param>
	<xsl:template name="title">
		Welcome
		<xsl:value-of select="$user"></xsl:value-of>
	</xsl:template>


	<xsl:template match="/profile/progress">
		<ol>
			<h3>User progress</h3>
			<li>
				Last Login:
				<span>
					<xsl:value-of select="last_login"></xsl:value-of>
				</span>
			</li>
			<li>
				Number of posts:
				<span>
					<xsl:value-of select="posts"></xsl:value-of>
				</span>
			</li>
			<li>
				Posts accepted:
				<span>
					<xsl:value-of select="post_accepted"></xsl:value-of>
				</span>
			</li>
			<li>
				Posts Rejected:
				<span>
					<xsl:value-of select="post_rejected"></xsl:value-of>
				</span>
			</li>
			<li>
				Number of articles selected as editor's pick of month:
				<span>
					<xsl:value-of select="ep"></xsl:value-of>
				</span>
			</li>
			<li>
				Number of articles selected as capture of month:
				<span>
					<xsl:value-of select="capture"></xsl:value-of>
				</span>
			</li>
			<li>
				Number of threads in discussion forum:
				<span>
					<xsl:value-of select="threads"></xsl:value-of>
				</span>
			</li>
		</ol>
	</xsl:template>

</xsl:stylesheet>

