package com.epam.ekc.storage.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Document(collection = "work")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Work implements Serializable, Identifiable {

  private static final long serialVersionUID = 1L;

  @Id
  @Null
  private String id;

  @NotNull
  @NotEmpty
  private String customerId;

  @Field("type")
  private String type;

  @Field("genre")
  private String genre;

  @Field("title")
  private String title;

  @DBRef
  @Field("agent")
  private Set<Agent> agents = new HashSet<>();

  @DBRef
  @Field("instance")
  private Set<Instance> instances = new HashSet<>();
}
