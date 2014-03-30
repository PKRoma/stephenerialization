package com.enragedginger.stephenerialization.preprocessing;

import com.enragedginger.stephenerialization.annotations.Stephenerialize;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import java.util.Set;
import java.util.TreeSet;

/**
 * Generates fields for classes that are being preprocessed.
 * @author Stephen Hopper
 */
public class StephenerializationPreprocessorFieldGenerator {

    /**
     * Generates StephenerializationPreprocessorField for a class that is Stephenerializable and is being preprocessed.
     * @param classElement The element refering to the annotated class.
     * @return The set of Stephenerializable fields in classElement.
     */
    public Set<StephenerializationPreprocessorField> generateFields(Element classElement) {
        final Set<StephenerializationPreprocessorField> instanceFields = new TreeSet<StephenerializationPreprocessorField>();
        for(VariableElement instanceField : ElementFilter.fieldsIn(classElement.getEnclosedElements())) {
            if (!instanceField.getModifiers().contains(Modifier.STATIC) && instanceField.getAnnotation(Stephenerialize.class) != null) {
                StephenerializationPreprocessorField preprocessorField = buildPreprocessorField(instanceField);
                instanceFields.add(preprocessorField);
            }
        }

        return instanceFields;
    }

    /**
     * Builds a preprocessor field.
     * @param variableElement The element refering to the field.
     * @return The StephenerializationPreprocessorField instance built from the variable element.
     */
    public StephenerializationPreprocessorField buildPreprocessorField(VariableElement variableElement) {
        final StephenerializationPreprocessorField field = new StephenerializationPreprocessorField();

        final Stephenerialize stephenerialize = variableElement.getAnnotation(Stephenerialize.class);
        field.setOrder(stephenerialize.priority());
        field.setVersion(stephenerialize.minVersion());

        final String typeName = variableElement.asType().toString();
        final boolean isPrimitive = variableElement.asType().getKind().isPrimitive();
        field.setFieldTypeName(typeName);
        field.setPrimitive(isPrimitive);
        field.setObjectInputStreamMethod(buildInputObjectStreamMethodName(isPrimitive, typeName));
        field.setObjectOutputStreamMethod(buildObjectOutputStreamMethodName(isPrimitive, typeName));

        final String fieldName = variableElement.getSimpleName().toString();
        field.setFieldName(fieldName);

        final String prefix = variableElement.asType().toString().equals("boolean") ? "is" : "get";
        final String upperCasedFieldName = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        field.setGetterName(prefix + upperCasedFieldName);
        field.setSetterName("set" + upperCasedFieldName);

        return field;
    }

    /**
     * Builds the name of the method on an ObjectOutputStream that should be used to write this field.
     * @param isPrimitive True if the field's data type is a primitive Java type.
     * @param typeName The name of the type.
     * @return The name of the method on an ObjectOutputStream that should be used to write this field.
     */
    private String buildObjectOutputStreamMethodName(boolean isPrimitive, String typeName) {
        if (isPrimitive) {
            if ("int".equals(typeName)) {
                return "writeInt";
            } else if ("long".equals(typeName)) {
                return "writeLong";
            } else if ("short".equals(typeName)) {
                return "writeShort";
            } else if ("byte".equals(typeName)) {
                return "writeByte";
            } else if ("float".equals(typeName)) {
                return "writeFloat";
            } else if ("double".equals(typeName)) {
                return "writeDouble";
            } else if ("boolean".equals(typeName)) {
                return "writeBoolean";
            } else if ("char".equals(typeName)) {
                return "writeChar";
            } else {
                throw new IllegalArgumentException("Type was supposedly primitive but was actually: " + typeName);
            }
        } else {
            return "writeObject";
        }

    }

    /**
     * Builds the name of the method on an ObjectInputStream that should be used to read this field.
     * @param isPrimitive True if the field's data type is a primitive Java type.
     * @param typeName The name of the type.
     * @return The name of the method on an ObjectInputStream that should be used to read this field.
     */
    private String buildInputObjectStreamMethodName(boolean isPrimitive, String typeName) {
        if (isPrimitive) {
            if ("int".equals(typeName)) {
                return "readInt";
            } else if ("long".equals(typeName)) {
                return "readLong";
            } else if ("short".equals(typeName)) {
                return "readShort";
            } else if ("byte".equals(typeName)) {
                return "readByte";
            } else if ("float".equals(typeName)) {
                return "readFloat";
            } else if ("double".equals(typeName)) {
                return "readDouble";
            } else if ("boolean".equals(typeName)) {
                return "readBoolean";
            } else if ("char".equals(typeName)) {
                return "readChar";
            } else {
                throw new IllegalArgumentException("Type was supposedly primitive but was actually: " + typeName);
            }
        } else {
            return "readObject";
        }

    }


}
