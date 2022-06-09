package br.com.example.properties;

public class ValueProperties {

    private static ValueProperties valueProperties;

    ApplicationProperties applicationProperties;

    private ValueProperties() {
        this.applicationProperties = new ApplicationProperties();
    }

    public static ValueProperties instance() {
        if (valueProperties == null) {
            valueProperties = new ValueProperties();
        }
        return valueProperties;
    }

    public Integer aesKeyBit() {
        String value = this.applicationProperties.readProperty("value.aes.key.bit");
        return Integer.valueOf(value);
    }

    public Integer tagLengthBit() {
        String value = this.applicationProperties.readProperty("value.tag.length.bit");
        return Integer.valueOf(value);
    }

    public Integer ivLengthByte() {
        String value = this.applicationProperties.readProperty("value.iv.length.byte");
        return Integer.valueOf(value);
    }

    public String algorithm() {
        return this.applicationProperties.readProperty("value.algorithm");
    }

    public String encryptAlgo() {
        return this.applicationProperties.readProperty("value.encrypt.algo");
    }
}
