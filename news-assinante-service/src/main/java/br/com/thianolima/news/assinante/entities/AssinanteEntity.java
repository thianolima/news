package br.com.thianolima.news.assinante.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Entity
@Table(name = "assinante", uniqueConstraints = @UniqueConstraint(columnNames= {"email"}, name="uk_assinante_email"))
public class AssinanteEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    String nome;

    String email;
}
