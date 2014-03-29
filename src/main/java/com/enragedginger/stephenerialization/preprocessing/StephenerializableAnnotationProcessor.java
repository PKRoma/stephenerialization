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
                    e.printStackTrace();
                }
            }
        }
        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, "OMG INBD IMDB LALALALALALA");
        //throw new IllegalArgumentException("WTF GUYS!!!!");
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

        Writer w = sourceFile.openWriter();

        //write package and class declaration name
        PackageElement p = processingEnv.getElementUtils().getPackageOf(element);
        w.write("package " + p.getQualifiedName().toString() + ";\n\n");
        w.write("import com.enragedginger.stephenerialization.StephenerializationException;\n" +
                "import java.io.ObjectInputStream;\n" +
                "import java.io.ObjectOutputStream;\n\n");
        w.write("class " + simpleGeneratedClassName + " {\n");

        //write stephenerialize method
        w.write("public static void stephenerialize(" + className + " object, ObjectOutputStream stream) {\n");

        if (fields != null && !fields.isEmpty()) {
            w.write("try {\n");
            w.write("stream.writeInt(" + stephenerializable.version() + ");\n");
            for (StephenerializationPreprocessorField field : fields) {
                w.write("stream.writeObject(object." + field.getGetterName() + "());\n");
            }

            w.write("} catch (Exception e) {\n");
            w.write("throw new StephenerializationException(\"An error occurred during Stephenerialization.\", e);\n");
            w.write("}\n");
        }
        w.write("}\n\n");

        //write read method
        w.write("public static void destephenerialize(" + className + " object, ObjectInputStream stream) {\n");
        if (fields != null && !fields.isEmpty()) {
            w.write("try {\n");
            w.write("final int version = stream.readInt();\n");

            Integer previousVersion = null;
            for (StephenerializationPreprocessorField field : fields) {
                if (previousVersion == null) {
                    previousVersion = field.getVersion();
                    w.write("if (version >= " + field.getVersion() + ") {\n");
                }

                if (previousVersion != field.getVersion()) {
                    w.write("}\n");
                    w.write("if (version >= " + field.getVersion() + ") {\n");
                }

                w.write("object." + field.getSetterName() + "(");
                if (!field.isPrimitive()) {
                    w.write("(" + field.getFieldTypeName() + ") ");
                } else {
                    w.write("(" + field.getCastType() + ") ");
                }
                //w.write("stream." + field.getObjectInputStreamMethod() + "());\n");
                w.write("stream.readObject());\n");

                previousVersion = field.getVersion();
            }
            w.write("}\n"); //close last if block
            w.write("} catch (Exception e) {\n");
            w.write("e.printStackTrace();\n");
            w.write("throw new StephenerializationException(\"An error occurred during Destephenerialization.\", e);\n");
            w.write("}\n");
        }
        w.write("}\n");


        //close class
        w.write("}\n");
        w.close();
        messager.printMessage(Diagnostic.Kind.NOTE, generatedClassName);
    }
}
