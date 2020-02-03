package com.epam.ekc.storage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Document(collection = "instance")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Instance implements Serializable, Identifiable {

  private static final long serialVersionUID = 1L;

  @Id
  private String id;

  @Field("type")
  private String type;

  @Field("title")
  private String title;

  @Field("language")
  private String language;

  @Field("publication_date")
  private Instant publicationDate;

  @DBRef
  @Field("work")
  @JsonIgnoreProperties("instances")
  private Work work;
}
