<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html"/>
<xsl:template match="/login">
	<xsl:element name="html">
		<xsl:element name="head">
			<xsl:element name="title">
				<xsl:value-of select="title/text()"/>
			</xsl:element>	
		</xsl:element>
		<xsl:element name="body">
			<xsl:element name="form">
				<xsl:for-each select="form/input">
					<xsl:if test="type != 'hidden'">
						<xsl:element name="div">
							<xsl:attribute name="class">
								<xsl:value-of select="classvalue"/>
							</xsl:attribute>
							<xsl:if test="type != 'submit'">
								<xsl:element name="label">
									<xsl:attribute name="for">
										<xsl:value-of select="name"/>
									</xsl:attribute>
									<xsl:value-of select="label"/>
								</xsl:element>
							</xsl:if>
							<xsl:element name="input">
								<xsl:attribute name="name">
									<xsl:value-of select="name"/>
								</xsl:attribute>
								<xsl:attribute name="type">
									<xsl:value-of select="type"/>
								</xsl:attribute>
								<xsl:if test="placeholder">
									<xsl:attribute name="placeholder">
										<xsl:value-of select="placeholder"/>
									</xsl:attribute>
								</xsl:if>
								<xsl:if test="value">
									<xsl:attribute name="value">
										<xsl:value-of select="value"/>
									</xsl:attribute>
								</xsl:if>
							</xsl:element>
						</xsl:element>
					</xsl:if>
					<xsl:if test="type = 'hidden'">
						<xsl:element name="input">
							<xsl:attribute name="name">
								<xsl:value-of select="name"/>
							</xsl:attribute>
							<xsl:attribute name="value">
								<xsl:value-of select="value"/>
							</xsl:attribute>
							<xsl:attribute name="type">
								<xsl:value-of select="type"/>
							</xsl:attribute>
						</xsl:element>
					</xsl:if>
				</xsl:for-each>
			</xsl:element>
		</xsl:element>
	</xsl:element>
</xsl:template>
</xsl:stylesheet> 