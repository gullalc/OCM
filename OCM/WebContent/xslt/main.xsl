<?xml version="1.0" encoding="UTF-8"?>

<!-- New document created with EditiX at Thu Mar 22 20:37:53 IST 2012 -->



<!-- New document created with EditiX at Thu Mar 15 11:44:20 IST 2012 -->

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">


	<xsl:output encoding="UTF-8" indent="yes" method="html"
		omit-xml-declaration="yes" doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"
		doctype-system="http://www.w3.org/TR/html4/loose.dtd" />
	<xsl:param name="user"></xsl:param>
	<xsl:param name="userid"></xsl:param>
	<xsl:template match="/">

		<html>
			<head>
				<title>
					Welcome
					<xsl:call-template name="title"></xsl:call-template>
				</title>
				<link href="style/homepage.css" rel="stylesheet" type="text/css" />
				<script type="text/javascript" src="scripts/script.js"></script>
			</head>
			<body>
				<center>
					<div id="header1">
						<p class="lft">
							Welcome
							<span>
								<xsl:call-template name="title"></xsl:call-template>
							</span>
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
													<xsl:apply-templates select="/root/categories/category"></xsl:apply-templates>
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
					<div id="header3">
						<div id="vmenu">
							<ol>
								<li class="heading">
									<button Id="Ba" class="ba" type="button" onClick="A()"></button>
									<span>Login</span>
									<div class="act" Id="a">
										<xsl:choose>
											<xsl:when test="$user='Guest'">
												<form>
													Username:
													<br></br>
													<input type="text" id="username"></input>
													<br></br>
													Password
													<input type="password" id="pass"></input>
													<br></br>

													<input type="button" class="subm" onclick="Login()"
														value="Login"></input>
													<input type="button" class="subm" onclick="Register()" value="Register"></input>
													<span id="error"></span>
													<br></br>
													<span>
														
														<input type="button" class="subm" onclick="window.location.href='http://localhost:8080/OCM/ForgotPassword.jsp'" value="Forgot password?"></input>
															
														
													</span>

												</form>
											</xsl:when>
											<xsl:otherwise>

												<div id="logout">
													<input type="hidden" id="uid" value="{$userid}"></input>
													<script>
														logout();
													</script>


												</div>
												<div>
													<ol>
														<li class="subm2" onclick="ViewProfile('{$userid}')">View Profile</li>
														<li class="subm2" onclick="lg()">Logout</li>
														<li class="subm2"><a href="http://localhost:8080/OCM/ForgotPassword.jsp">Forgot Password</a></li>
													</ol>
												</div>
											</xsl:otherwise>

										</xsl:choose>
									</div>
								</li>
								<li class="heading">
									<button Id="Bb" class="bi" type="button" onClick="B()"></button>
									<span>Category</span>
									<div class="inact" Id="b">
										<ul id="Category">
											<xsl:call-template name="List"></xsl:call-template>
										</ul>


									</div>
								</li>
								<li class="heading">
									<button Id="Bc" class="bi" type="button" onClick="C()"></button>
									<span>Moderator</span>
									<ol class="inact" Id="c">
										<li>
											<ul id="Moderator">
												<xsl:apply-templates select="//moderators"></xsl:apply-templates>
											</ul>
										</li>

									</ol>
								</li>
							</ol>
						</div>
						<div id="gallery">
							<ol>
								<li>
									<button Id="Previous" onClick="prev()"></button>
								</li>
								<li>
									<div Id="v" class="vis">
										<h1>Word Of the Day</h1>
										<xsl:apply-templates select="/root/word_of_day"></xsl:apply-templates>
									</div>
								</li>
								<li>
									<div Id="w" class="hid">
										<h1>Thought Of the Day</h1>
										<xsl:apply-templates select="/root/thought_of_day"></xsl:apply-templates>
									</div>
								</li>
								<li>
									<div Id="x" class="hid">
										<h1>Capture Of the Month</h1>
										<xsl:apply-templates select="/root/capture"></xsl:apply-templates>
									</div>
								</li>
								<li>
									<div Id="y" class="hid">
										<h1>Editor's Pick</h1>
										<xsl:apply-templates select="/root/editor_pick">
										</xsl:apply-templates>
									</div>
								</li>
								<li>
									<div Id="z" class="hid">
										<h1>Campus Buzz</h1>
										<xsl:apply-templates select="//news"></xsl:apply-templates>
									</div>
								</li>
								<li>
									<button Id="next" onClick="next()"></button>
								</li>
							</ol>
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
						<div id="left_content">
							<xsl:apply-templates select="//articles">
							
							</xsl:apply-templates>
						</div>
						<div id="right_content">
							<div id="most_popular">
								<ul>
									<li>
										Most Popular
										<ol>
											<xsl:apply-templates select="//most_popular"></xsl:apply-templates>
										</ol>
									</li>
								</ul>
							</div>
							<div id="archives">
								<ul>
									<li>
										Archives
										<ol>
											<xsl:apply-templates select="//archives"></xsl:apply-templates>
										</ol>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div id="footer">
						<h6>Web site developed by 'CODEKNOCKS' for IBM TGMC 2011 under the
							guidance of Mrs. Sumeet K. Sehra, Guru Nanak Dev Engineering
							College, Ludhiana</h6>
					</div>
				</center>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="/root/categories/category">

		<xsl:variable name="id" select="@id"></xsl:variable>
		<option value="{.}">
			<xsl:value-of select="." />
		</option>
	</xsl:template>
	<xsl:template name="List">
		<xsl:for-each select="/root/categories/category">
			<xsl:variable name="id" select="@id"></xsl:variable>
			<li>
				<a href="{$id}">
					<xsl:value-of select="." />
				</a>
			</li>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="/root/moderators">
		<xsl:for-each select="id">
			
			<li>
				<xsl:variable name="link" select="link"></xsl:variable>
				<xsl:variable name="t">
			<xsl:value-of select='concat("http://localhost:8080/OCM/Profile?id=", $link)'/>
			</xsl:variable>
				<a href="{$t}" ><xsl:value-of select="moderator"/></a>
			</li>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="/root/word_of_day">
		<h2>Word:</h2>
		<h3>
			<xsl:value-of select="word" />
		</h3>
		<h2>Meaning:</h2>
		<h4>
			<xsl:value-of select="meaning" />
		</h4>
	</xsl:template>
	<xsl:template match="/root/thought_of_day">
		<h2>Thought:</h2>
		<h3>
			&quot;
			<xsl:value-of select="thought" />
			&quot;
		</h3>
		<h2>Author:</h2>
		<h4>
			<xsl:value-of select="author" />
		</h4>
	</xsl:template>
	<xsl:template match="/root/capture">
		<xsl:variable name="link" select="link"></xsl:variable>
		<xsl:variable name="image">
			<xsl:value-of select='concat("http://localhost:8080/OCM/ViewPhoto?post=photos&amp;id=", $link)'/>
			</xsl:variable>
		<xsl:variable name="lin">
			<xsl:value-of select='concat("http://localhost:8080/OCM/Preview?id=", $link)'/>
		</xsl:variable>
		<a href="{$lin}">
			<img src="{$image}" alt="" />
		</a>
	</xsl:template>
	<xsl:template match="/root/editor_pick">
		<div id="capture">
			<div id="text">
				<h2>
					<xsl:value-of select="title" />
				</h2>
				<p>
					<xsl:value-of select="text" />
					....
				</p>
				<xsl:variable name="link" select="link">
				
				</xsl:variable>
				<xsl:variable name="src">
				<xsl:value-of select='concat("http://localhost:8080/OCM/ViewArticle?id=", $link)'/>
				</xsl:variable>
				<a href="{$src}">Read More</a>
			</div>
			<xsl:variable name="image">
			<xsl:value-of select='concat("http://localhost:8080/OCM/ViewPhoto?post=article&amp;id=", //editor_pick/image)'/>
			</xsl:variable>
			<img src="{$image}" alt="" />
		</div>
	</xsl:template>

	<xsl:template name="title">

		<xsl:value-of select="$user"></xsl:value-of>
	</xsl:template>
	<xsl:template match="//news">
	<xsl:for-each select="id">
	<div id="news2">
	<h4><xsl:value-of select="title"/>	</h4>
	<h5><xsl:value-of select="text"/>
	</h5>
	</div>
	</xsl:for-each>
	</xsl:template>
	<xsl:template match="//most_popular">
	<xsl:for-each select="mp">
	
	<li>
	<xsl:variable name="link" select="link">
				
				</xsl:variable>
				<xsl:variable name="src">
				<xsl:value-of select='concat("http://localhost:8080/OCM/ViewArticle?id=", $link)'/>
				</xsl:variable>
				<a href="{$src}"><xsl:value-of select="title"/></a>
	</li>
	</xsl:for-each>
	</xsl:template>
	<xsl:template match="//archives">
	<xsl:for-each select="node">
	<li>
	<xsl:value-of select="month"/> ,
	<xsl:value-of select="year"/> -
	<xsl:value-of select="count"/>
	</li>
	</xsl:for-each>
	</xsl:template>
	<xsl:template match="//articles">
	<xsl:for-each select="article">
	<div id="articles" class="odd">
	<xsl:variable name="link" select="link">
				
				</xsl:variable>
		<div id="text">
		<h4>
		<xsl:value-of select="title"/></h4>
		<xsl:variable name="tes">
		<xsl:value-of select="text"/>
		</xsl:variable>
		<xsl:variable name="txt">
			<xsl:value-of select='substring($tes,0,1000)'/>
		</xsl:variable>
		<xsl:value-of select="$txt"/>
		
				<xsl:variable name="src">
				<xsl:value-of select='concat("http://localhost:8080/OCM/ViewArticle?id=", $link)'/>
				</xsl:variable><br>
				</br>
				<a href="{$src}">Read More</a>
		
		</div>
		<xsl:variable name="image">
			<xsl:value-of select='concat("http://localhost:8080/OCM/ViewPhoto?post=article&amp;id=", $link)'/>
			</xsl:variable>
			<img src="{$image}"/>

	</div>
	</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>





