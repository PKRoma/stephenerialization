package com.enragedginger.stephenerialization.preprocessing;

import com.enragedginger.stephenerialization.annotations.Stephenerialize;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Stephen on 3/29/2014.
 */
public class StephenerializationPreprocessorFieldGenerator {

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
        field.setCastType(buildCastType(isPrimitive, typeName));

        final String fieldName = variableElement.getSimpleName().toString();
        field.setFieldName(fieldName);

        final String prefix = variableElement.asType().toString().equals("boolean") ? "is" : "get";
        final String upperCasedFieldName = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        field.setGetterName(prefix + upperCasedFieldName);
        field.setSetterName("set" + upperCasedFieldName);

        return field;
    }

    private String buildCastType(boolean isPrimitive, String typeName) {
        if (isPrimitive) {
            if ("int".equals(typeName)) {
                return "Integer";
            } else if ("long".equals(typeName)) {
                return "Long";
            } else if ("short".equals(typeName)) {
                return "Short";
            } else if ("byte".equals(typeName)) {
                return "Byte";
            } else if ("float".equals(typeName)) {
                return "Float";
            } else if ("double".equals(typeName)) {
                return "Double";
            } else if ("boolean".equals(typeName)) {
                return "Boolean";
            } else if ("char".equals(typeName)) {
                return "Character";
            } else {
                throw new IllegalArgumentException("Type was supposedly primitive but was actually: " + typeName);
            }
        } else {
            return "readObject";
        }
    }

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
