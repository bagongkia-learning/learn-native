package com.bagongkia.learn.report;

import org.springframework.aot.hint.ExecutableMode; // <-- Ensure this import is present
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;

import java.util.List;

public class XmlBeansRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        
        // =========================================================================
        // 1. EXTENSIVE REFLECTION: HEAVY LIFTING (No changes needed here)
        // =========================================================================
        hints.reflection().registerType(org.apache.poi.util.IOUtils.class, 
            MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
            MemberCategory.INVOKE_DECLARED_METHODS, MemberCategory.INVOKE_PUBLIC_METHODS,
            MemberCategory.DECLARED_FIELDS, MemberCategory.PUBLIC_FIELDS);

        hints.reflection().registerType(TypeReference.of("sun.awt.X11FontManager"), 
            MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
            MemberCategory.INVOKE_DECLARED_METHODS, MemberCategory.INVOKE_PUBLIC_METHODS,
            MemberCategory.DECLARED_FIELDS, MemberCategory.PUBLIC_FIELDS);

        // =========================================================================
        // 2. CONSTRUCTOR-ONLY REFLECTION (No changes needed here)
        // =========================================================================
        List<Class<?>> constructorOnlyClasses = List.of(
            org.apache.logging.log4j.message.ParameterizedMessageFactory.class,
            org.apache.commons.compress.archivers.zip.AbstractUnicodeExtraField.class,
            org.apache.commons.compress.archivers.zip.AsiExtraField.class,
            org.apache.commons.compress.archivers.zip.ExtraFieldUtils.class,
            org.apache.commons.compress.archivers.zip.GeneralPurposeBit.class,
            org.apache.commons.compress.archivers.zip.JarMarker.class,
            org.apache.commons.compress.archivers.zip.ParallelScatterZipCreator.class,
            org.apache.commons.compress.archivers.zip.ResourceAlignmentExtraField.class,
            org.apache.commons.compress.archivers.zip.UnicodeCommentExtraField.class,
            org.apache.commons.compress.archivers.zip.UnicodePathExtraField.class,
            org.apache.commons.compress.archivers.zip.UnparseableExtraFieldData.class,
            org.apache.commons.compress.archivers.zip.UnrecognizedExtraField.class,
            org.apache.commons.compress.archivers.zip.X000A_NTFS.class,
            org.apache.commons.compress.archivers.zip.X0014_X509Certificates.class,
            org.apache.commons.compress.archivers.zip.X0015_CertificateIdForFile.class,
            org.apache.commons.compress.archivers.zip.X0016_CertificateIdForCentralDirectory.class,
            org.apache.commons.compress.archivers.zip.X0017_StrongEncryptionHeader.class,
            org.apache.commons.compress.archivers.zip.X0019_EncryptionRecipientCertificateList.class,
            org.apache.commons.compress.archivers.zip.X5455_ExtendedTimestamp.class,
            org.apache.commons.compress.archivers.zip.X7875_NewUnix.class,
            org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField.class,
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry.class,
            org.apache.commons.compress.archivers.zip.ZipEncodingHelper.class,
            org.apache.commons.compress.archivers.zip.ZipUtil.class
        );
        for (Class<?> clazz : constructorOnlyClasses) {
            hints.reflection().registerType(clazz, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);
        }

        // =========================================================================
        // 3. XMLBEANS INTERNAL STRUCTURES & PARSER FACTORIES (FIXED WITH ExecutableMode)
        // =========================================================================
        hints.reflection().registerType(java.lang.Class.class, builder -> {});
        
        hints.reflection().registerType(org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem.class, builder -> builder
            .withMethod("get", List.of(), ExecutableMode.INVOKE)
            .withMethod("getNoType", List.of(), ExecutableMode.INVOKE));

        hints.reflection().registerType(org.apache.xmlbeans.impl.schema.PathResourceLoader.class, builder -> builder
            .withConstructor(List.of(TypeReference.of("java.io.File[]")), ExecutableMode.INVOKE));

        hints.reflection().registerType(org.apache.xmlbeans.impl.schema.SchemaTypeLoaderImpl.class, builder -> builder
            .withMethod("build", List.of(TypeReference.of("org.apache.xmlbeans.SchemaTypeLoader[]"), TypeReference.of("org.apache.xmlbeans.ResourceLoader"), TypeReference.of("java.lang.ClassLoader")), ExecutableMode.INVOKE)
            .withMethod("getContextTypeLoader", List.of(), ExecutableMode.INVOKE));

        hints.reflection().registerType(org.apache.xmlbeans.impl.schema.SchemaTypeSystemCompiler.class, builder -> builder
            .withMethod("compile", List.of(
                TypeReference.of("java.lang.String"), TypeReference.of("org.apache.xmlbeans.SchemaTypeSystem"), TypeReference.of("org.apache.xmlbeans.XmlObject[]"),
                TypeReference.of("org.apache.xmlbeans.BindingConfig"), TypeReference.of("org.apache.xmlbeans.SchemaTypeLoader"), TypeReference.of("org.apache.xmlbeans.Filer"), TypeReference.of("org.apache.xmlbeans.XmlOptions")
            ), ExecutableMode.INVOKE));

        hints.reflection().registerType(org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl.class, builder -> builder
            .withConstructor(List.of(TypeReference.of("java.lang.Class")), ExecutableMode.INVOKE));

        hints.reflection().registerType(org.apache.xmlbeans.impl.store.Locale.class, builder -> builder
            .withMethod("nodeToCursor", List.of(TypeReference.of("org.w3c.dom.Node")), ExecutableMode.INVOKE)
            .withMethod("nodeToXmlObject", List.of(TypeReference.of("org.w3c.dom.Node")), ExecutableMode.INVOKE)
            .withMethod("nodeToXmlStream", List.of(TypeReference.of("org.w3c.dom.Node")), ExecutableMode.INVOKE)
            .withMethod("streamToNode", List.of(TypeReference.of("javax.xml.stream.XMLStreamReader")), ExecutableMode.INVOKE));

        // =========================================================================
        // 4. OPENXML SCHEMAS & DOCUMENT IMPLEMENTATIONS (FIXED WITH ExecutableMode)
        // =========================================================================
        hints.reflection().registerType(TypeReference.of("org.openxmlformats.schemas.spreadsheetml.x2006.main.STFontScheme$Enum"), builder -> {});
        hints.reflection().registerType(TypeReference.of("org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType$Enum"), builder -> {});
        hints.reflection().registerType(TypeReference.of("org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType$Enum"), 
            builder -> builder.withField("table"));

        List<String> schemaImpls = List.of(
            "org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.CTPropertiesImpl",
            "org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.PropertiesDocumentImpl",
            "org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl.CTPropertiesImpl",
            "org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl.PropertiesDocumentImpl",
            "org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.impl.STXstringImpl",
            "org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CalcChainDocumentImpl",
            "org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.ChartsheetDocumentImpl",
            "org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CommentsDocumentImpl",
            "org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTAuthorsImpl",
            "org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTAutoFilterImpl",
            "org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTBookViewImpl",
            "org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTBookViewsImpl"
        );

        for (String implName : schemaImpls) {
            hints.reflection().registerType(TypeReference.of(implName), builder -> builder
                .withConstructor(List.of(TypeReference.of("org.apache.xmlbeans.SchemaType")), ExecutableMode.INVOKE));
        }

        // =========================================================================
        // 5. AWT FIELD ACCESSIBILITY
        // =========================================================================
        hints.reflection().registerType(java.awt.Font.class, MemberCategory.DECLARED_FIELDS);
    }
}
