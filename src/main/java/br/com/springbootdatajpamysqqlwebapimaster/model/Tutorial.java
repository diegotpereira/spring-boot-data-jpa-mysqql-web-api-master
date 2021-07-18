package br.com.springbootdatajpamysqqlwebapimaster.model;

import javax.persistence.*;

@Entity
@Table(name = "tutoriais")
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "published")
	private boolean published;

    public Tutorial() {
    }

    public Tutorial(String titulo, String descricao, boolean published) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.published = published;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Tutorial [descricao=" + descricao + ", id=" + id + ", published=" + published + ", titulo=" + titulo
                + "]";
    }
}
