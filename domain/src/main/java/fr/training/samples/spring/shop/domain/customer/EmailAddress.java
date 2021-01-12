package fr.training.samples.spring.shop.domain.customer;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class EmailAdress implements Serializable {
    private Long serialVersionUID;

    private String value;

    EmailAdress() {

    }

    private EmailAdress(String value) {
        this.value = value;
    }

    public static EmailAdress of(String email){
        return new EmailAdress(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        EmailAdress that = (EmailAdress) o;

        return new EqualsBuilder().append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }
}
