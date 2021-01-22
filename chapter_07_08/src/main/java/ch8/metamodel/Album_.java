package ch8.metamodel;

import ch8.entities.Album;
import ch8.entities.Singer;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Album.class)
public abstract class Album_ {

	public static volatile SingularAttribute<Album, Singer> singer;
	public static volatile SingularAttribute<Album, Date> releaseDate;
	public static volatile SingularAttribute<Album, Long> id;
	public static volatile SingularAttribute<Album, String> title;
	public static volatile SingularAttribute<Album, Integer> version;

	public static final String SINGER = "singer";
	public static final String RELEASE_DATE = "releaseDate";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String VERSION = "version";

}

