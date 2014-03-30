package com.enragedginger.stephenerialization.preprocessing;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import com.enragedginger.stephenerialization.annotations.*;
import jdk.nashorn.internal.codegen.CompilationException;

/**
 * Processes {@link Stephenerializable} annotations and builds stephenerialization logic
 * at compile time instead of run time.
 *
 * @author Stephen Hopper
 */
@SupportedAnnotationTypes(value = {"com.enragedginger.stephenerialization.annotations.Stephenerializable"})
public class StephenerializableAnnotationProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager messager = processingEnv.getMessager();

        for (TypeElement annotation : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                try {
                    processAnnotation(element, messager);
                } catch (Exception e) {
                    throw new RuntimeException("An error occurred while preprocessing Stephenerialization annotations.", e);
                }
            }
        }
        return true;
    }

    private void processAnnotation(Element element, Messager messager) throws IOException, ClassNotFoundException {
        Stephenerializable stephenerializable = element.getAnnotation(Stephenerializable.class);
        String className = element.toString();
        String simpleName = element.getSimpleName().toString();
        String generatedClassName = className + "Stephenerializer";
        String simpleGeneratedClassName = simpleName + "Stephenerializer";
        Filer filer = processingEnv.getFiler();
        JavaFileObject sourceFile = filer.createSourceFile(generatedClassName, element);

        StephenerializationPreprocessorFieldGenerator generator = new StephenerializationPreprocessorFieldGenerator();
        Set<StephenerializationPreprocessorField> fields = generator.generateFields(element);

        PreprocessingWriter w = new PreprocessingWriter(sourceFile.openWriter());

        writePackageImportsAndClass(w, element, simpleGeneratedClassName);

        w.indent();
        writeWriteMethod(w, className, fields, stephenerializable);
        w.writeLine("");
        writeReadMethod(w, className, fields);
        w.deindent();

        //close class
        w.writeLine("}");
        w.close();
        messager.printMessage(Diagnostic.Kind.NOTE, generatedClassName);
    }

    /**
     * Writes the opening of the stephenerializer class.
     *
     * @param w                        The class's writer.
     * @param element                  The element which references the class which is being processed.
     * @param simpleGeneratedClassName The simple name of the generated class.
     * @throws IOException If an error occurs.
     */
    private void writePackageImportsAndClass(PreprocessingWriter w, Element element, String simpleGeneratedClassName) throws IOException {
        //write package and class declaration name
        PackageElement p = processingEnv.getElementUtils().getPackageOf(element);
        w.writeLine("package " + p.getQualifiedName().toString() + ";\n");
        w.writeLine("import com.enragedginger.stephenerialization.StephenerializationException;");
        w.writeLine("import java.io.ObjectInputStream;");
        w.writeLine("import java.io.ObjectOutputStream;\n");
        w.writeLine("class " + simpleGeneratedClassName + " {");
    }

    /**
     * Creates the read method on the class.
     *
     * @param w                  The class's writer.
     * @param className          The fullname of the class for which this stephenerializer is being created.
     * @param fields             The fields on the object.
     * @param stephenerializable The Stephenerializable annotation on the class.
     * @throws IOException If an error occurs.
     */
    private void writeWriteMethod(PreprocessingWriter w, String className, Set<StephenerializationPreprocessorField> fields,
                                  Stephenerializable stephenerializable) throws IOException {
        //write stephenerialize method
        w.writeLine("public static void stephenerialize(" + className + " object, ObjectOutputStream stream) {");

        w.indent();
        if (fields != null && !fields.isEmpty()) {
            w.writeLine("try {");
            w.indent();
            w.writeLine("stream.writeInt(" + stephenerializable.version() + ");");
            for (StephenerializationPreprocessorField field : fields) {
                w.writeLine("stream." + field.getObjectOutputStreamMethod() + "(object." + field.getGetterName() + "());");
            }
            w.deindent();
            w.writeLine("} catch (Exception e) {");
            w.indent();
            w.writeLine("throw new StephenerializationException(\"An error occurred during Stephenerialization.\", e);");
            w.deindent();
            w.writeLine("}");
        }
        w.deindent();
        w.writeLine("}");
    }

    /**
     * Creates the write method on the class.
     *
     * @param w         The class's writer.
     * @param className The fullname of the class for which this stephenerializer is being created.
     * @param fields    The fields on the object.
     * @throws IOException If an error occurs.
     */
    private void writeReadMethod(PreprocessingWriter w, String className, Set<StephenerializationPreprocessorField> fields) throws IOException {
        //write read method
        w.writeLine("public static void destephenerialize(" + className + " object, ObjectInputStream stream) {");
        w.indent();
        if (fields != null && !fields.isEmpty()) {
            w.writeLine("try {");
            w.indent();
            w.writeLine("final int version = stream.readInt();");

            Integer previousVersion = null;
            for (StephenerializationPreprocessorField field : fields) {
                if (previousVersion == null) {
                    previousVersion = field.getVersion();
                    w.writeLine("if (version >= " + field.getVersion() + ") {");
                }

                if (previousVersion != field.getVersion()) {
                    w.writeLine("}");
                    w.writeLine("if (version >= " + field.getVersion() + ") {");
                }

                w.indent();
                StringBuffer buffy = new StringBuffer();
                buffy.append("object." + field.getSetterName() + "(");
                if (!field.isPrimitive()) {
                    buffy.append("(" + field.getFieldTypeName() + ") ");
                }
                buffy.append("stream." + field.getObjectInputStreamMethod() + "());");
                w.writeLine(buffy.toString());
                w.deindent();
                previousVersion = field.getVersion();
            }
            w.writeLine("}"); //close last if block
            w.deindent();
            w.writeLine("} catch (Exception e) {");
            w.indent();
            w.writeLine("throw new StephenerializationException(\"An error occurred during Destephenerialization.\", e);");
            w.deindent();
            w.writeLine("}");
        }
        w.deindent();
        w.writeLine("}");
    }
}
