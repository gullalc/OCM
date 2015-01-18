<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:param name="likes" select="7"></xsl:param>

	<xsl:param name="likes_enable" select="'true'"></xsl:param>

	<xsl:param name="post" select="'Article'"></xsl:param>

	<xsl:param name="id" select="1"></xsl:param>
	<xsl:output method="html"/>
	
	<xsl:template match="/">
	
		<form>
		
		<table>
		<tr>
		<h6>
		<td>
		Comments <xsl:value-of select="count(root/comment)"/></td> 
		<td> 
		Likes <xsl:value-of select="$likes"/></td>
		</h6>
		</tr>
		<tr>
		<td>
		<input type="button" onclick="Comment('comment')" value="Comment" />
		</td>
		<td>
		
			<xsl:choose>
			<xsl:when test="$likes_enable= 'false' ">
				You like this post
			</xsl:when>
			<xsl:otherwise>
				<input type="button" onclick="Like('{$post}','{$id}')" value="Like" />
			</xsl:otherwise>
			</xsl:choose>
		</td>
		</tr>
		</table>
		<div id="comment" style="display:none" >
			<br/>
			<textarea rows="5" cols="50" id="cont"></textarea><br/>
			<input type="button" onclick="comm('{$post}',{$id})" value="Comment"></input>
		</div>
		<div>
			<xsl:apply-templates select="/root/comment"></xsl:apply-templates>
		</div>
		</form>
		
		
	</xsl:template>

<xsl:template match="/root/comment">

<div class="commentbox" >
<xsl:variable name="id">
	<xsl:value-of select="/root/comment/uid"/>
</xsl:variable>
	<xsl:choose>
		<xsl:when test="username">
		
		<h4><a href='http://localhost:8080/OCM/Profile?id={$id}'><xsl:value-of select="username"/></a> says:</h4>
		
		<xsl:value-of select="cont"/>
		</xsl:when>
		<xsl:when test="email">
		<h4>Guest(<xsl:value-of select="email"/>) says</h4>
		<xsl:value-of select="cont"/>
		</xsl:when>
	</xsl:choose>

</div>

</xsl:template>
</xsl:stylesheet>
