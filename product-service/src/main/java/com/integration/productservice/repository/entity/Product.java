package com.integration.productservice.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "category_id")
    private long categoryId;

    @Column(name = "brand_id")
    private long brandId;

    @Column
    private String barcode;

    @Column
    private String title;

    @Column()
    private boolean status;

    @Column(name = "list_price", columnDefinition = "numeric(8,2)")
    @ColumnDefault("0")
    private double listPrice = 0;

    @ColumnDefault("0")
    @Column(name = "sale_price", columnDefinition = "numeric(8,2)")
    private double salePrice = 0;

    @Column(name = "vat_rate")
    private double vatRate;

    @Column
    private String currency;

    @ColumnDefault("0")
    @Column
    private long quantity = 0;

    @Column
    private String description;

    @Column(name = "short_description")
    private String shortDescription;

    @Column
    private List<String> images;

    @Column(name = "main_product_code")
    private String mainProductCode;

    @Column(name = "model_code")
    private String modelCode;

    @Column(name = "platform_code")
    private String platformCode;

    @Column(name = "variant_properties", columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<VariantProperties> variantProperties;

    @Builder.Default
    @Column(name = "updated_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

    @Builder.Default
    @Column(name = "created_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @ColumnDefault("false")
    @Column(name = "is_deleted")
    private boolean deleted = false;
}

