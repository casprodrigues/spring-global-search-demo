package dev.crodrigues.globalsearch.product;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product {
    
    @Id
    private Integer id;

    private String productCode;
    private String productName;
    private String description;
    private BigDecimal standardCost;
    private BigDecimal listPrice;
    private Integer targetLevel;
    private Integer reorderLevel;
    private Integer minimumReorderQuantity;
    private String quantityPerUnit;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean discontinued;

    private String category;

    public String getProductCodeAndPriceAsString() {
        return String.format("%s - $%.2f", productCode, listPrice);
    }

}