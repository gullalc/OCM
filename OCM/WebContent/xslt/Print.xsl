<?xml version="1.0" encoding="UTF-8" ?>

<!-- New document created with EditiX at Tue Mar 13 00:27:07 IST 2012 -->

<xsl:stylesheet 
	version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format">

	<xsl:output method="xml" indent="yes"/>
	
	<xsl:template match="/">

		<fo:root>
			<fo:layout-master-set>
      <fo:simple-page-master page-height="11in" page-width="8.5in" master-name="only">
         <fo:region-body 
   region-name="xsl-region-body"  
            margin="0.7in" />
   <fo:region-before
            region-name="xsl-region-before" 
            extent="0.7in" />
         <fo:region-after 
   region-name="xsl-region-after"
           extent="0.7in" />
      </fo:simple-page-master>
   </fo:layout-master-set>
		
			<fo:page-sequence master-reference="only">
			
				<fo:flow flow-name="xsl-region-body">
				<fo:block  
        break-before="page" 
        break-after="page"
          space-after="4in" 
          space-before="3in" 
          space-before.conditionality="retain" 
          font-size="48pt" 
          text-align="center">
  GenRev Online College Magazine
</fo:block>
	<fo:block > 
           <xsl:apply-templates select="//title"></xsl:apply-templates>
           </fo:block>
           <fo:block  text-align="center" space-before="1cm" space-after="1cm" > 
           
           <xsl:apply-templates select="//id"></xsl:apply-templates> 
         </fo:block > 
          
         

   <fo:block>
   <xsl:apply-templates select="//content"></xsl:apply-templates>
   </fo:block>
					
				</fo:flow>
		  	</fo:page-sequence>
		</fo:root>
		
	</xsl:template>

<xsl:template match="//title">

<xsl:value-of select="."/>

</xsl:template>


<xsl:template match="//id" >


<xsl:variable name="src2">

<xsl:value-of select="."/>

</xsl:variable>
<xsl:variable name="src">
<xsl:value-of select='concat("http://localhost:8080/OCM/ViewPhoto?post=article&amp;id=", //id)'/>
</xsl:variable>
<fo:external-graphic     src="url({$src})"  content-height="2in" content-width="3.5in"    />

</xsl:template>

<xsl:template match="//content">

<xsl:value-of select="."/>


</xsl:template>
</xsl:stylesheet>
