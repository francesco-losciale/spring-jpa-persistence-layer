package persistence.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.persistence.annotation.SoftDeleteActive;
import com.persistence.base.BaseEntity;


@Entity
@Table(name="TEST_COLLECTION")
@SequenceGenerator(name="SQ_TEST_COLLECTION", initialValue=1, allocationSize=100)
@SoftDeleteActive
@Where(clause="DATE_DELETE is null") // hibernate annotation! no good... please solve this TODO
public class TestCollectionEntity extends BaseEntity {

	
	private Long id;
	private String releaseName;
	private Set<TestEntity> listTestEntity;
	
	@Id
	@SequenceGenerator(name="SQ_TEST_COLLECTION_GENERATOR", sequenceName="SQ_TEST_COLLECTION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_TEST_COLLECTION_GENERATOR")
	@Column(name="ID_TEST_COLLECTION")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="RELEASE_NAME")
	public String getReleaseName() {
		return releaseName;
	}
	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}
	
	@OneToMany(mappedBy="testCollection", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	public Set<TestEntity> getListTest() {
		return listTestEntity;
	}
	public void setListTest(Set<TestEntity> listTestEntity) {
		this.listTestEntity = listTestEntity;
	}
		
}
