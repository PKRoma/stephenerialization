package com.enragedginger.stephenerialization.preprocessing;

/**
 * Created by Stephen on 3/29/2014.
 */
public class StephenerializationPreprocessorField implements Comparable<StephenerializationPreprocessorField> {

    private int version;
    private int order;
    private String fieldName;
    private String getterName;
    private String setterName;
    private String fieldTypeName;
    private String objectInputStreamMethod;
    private boolean isPrimitive;
    private String castType;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getGetterName() {
        return getterName;
    }

    public void setGetterName(String getterName) {
        this.getterName = getterName;
    }

    public String getSetterName() {
        return setterName;
    }

    public void setSetterName(String setterName) {
        this.setterName = setterName;
    }

    public String getFieldTypeName() {
        return fieldTypeName;
    }

    public void setFieldTypeName(String fieldTypeName) {
        this.fieldTypeName = fieldTypeName;
    }

    public String getObjectInputStreamMethod() {
        return objectInputStreamMethod;
    }

    public void setObjectInputStreamMethod(String objectInputStreamMethod) {
        this.objectInputStreamMethod = objectInputStreamMethod;
    }

    public boolean isPrimitive() {
        return isPrimitive;
    }

    public void setPrimitive(boolean isPrimitive) {
        this.isPrimitive = isPrimitive;
    }

    public String getCastType() {
        return castType;
    }

    public void setCastType(String castType) {
        this.castType = castType;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object other) {
        boolean equals = false;
        if (other instanceof StephenerializationPreprocessorField) {
            StephenerializationPreprocessorField that = (StephenerializationPreprocessorField) other;
            equals = this.compareTo(that) == 0;
        }
        return equals;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(StephenerializationPreprocessorField other) {
        int value = 0;
        if (other != null) {
            if (this.version != other.version) {
                value = ((Integer) this.version).compareTo(other.version);
            } else if (this.order != other.order) {
                value = ((Integer) this.order).compareTo(other.order);
            } else if (this.fieldName != null) {
                value = this.fieldName.compareTo(other.fieldName);
            } else {
                value = 1;
            }
        }
        return value;
    }

}
