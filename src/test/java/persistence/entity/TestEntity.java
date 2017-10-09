package persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.persistence.annotation.SoftDeleteActive;
import com.persistence.base.BaseEntity;


@Entity
@Table(name="TEST")
@SoftDeleteActive
@Where(clause="DATE_DELETE is null") // hibernate annotation! no good... please solve this TODO
public class TestEntity extends BaseEntity {

	private Long id;
	private TestCollectionEntity testCollection;
	
	@Id
	@SequenceGenerator(name="SQ_TEST_GENERATOR", sequenceName="SQ_TEST")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_TEST_GENERATOR")
	@Column(name="ID_TEST")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_TEST_COLLECTION", nullable=false)
	public TestCollectionEntity getTestCollection() {
		return testCollection;
	}

	public void setTestCollection(TestCollectionEntity testCollection) {
		this.testCollection = testCollection;
	}

	
}
