/**
 * 
 */
package co.com.rappi.cubes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Represents a query over a cube
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
@Entity
public class Query {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    /** Specifies the when it must me executed in a problem*/
    @Column
    @NotNull
    private int step;
    
    /** Specifies the position in the first axe of the first coordinate*/
    @Column
    @NotNull
	private int x1;
    
    /** Specifies the position in the second axe of the first coordinate*/
    @Column
    @NotNull
	private int y1;
    
    /** Specifies the position in the third axe of the first coordinate*/
    @Column
    @NotNull
	private int z1;
    
    /** Specifies the position in the first axe of the last coordinate*/
    @Column
    @NotNull
	private int x2;
    
    /** Specifies the position in the second axe of the last coordinate*/
    @Column
    @NotNull
	private int y2;
    
    /** Specifies the position in the third axe of the last coordinate*/
    @Column
    @NotNull
	private int z2;

    /**
     * Need for JPA compliance
     */
	public Query() {
	}

	public Query(int x1, int y1, int z1, int x2, int y2, int z2) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getZ1() {
		return z1;
	}

	public void setZ1(int z1) {
		this.z1 = z1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getZ2() {
		return z2;
	}

	public void setZ2(int z2) {
		this.z2 = z2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

}
