package ch8.metamodel;

import ch8.entities.Instrument;
import ch8.entities.Singer;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Instrument.class)
public abstract class Instrument_ {

	public static volatile SetAttribute<Instrument, Singer> singers;
	public static volatile SingularAttribute<Instrument, String> instrumentId;

	public static final String SINGERS = "singers";
	public static final String INSTRUMENT_ID = "instrumentId";

}

