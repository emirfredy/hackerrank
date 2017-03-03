/**
 * 
 */
package co.com.rappi.cubes.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Represents an update over a cube
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
@Entity
public class Update {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    /** Specifies the when it must me executed in a problem*/
    @Column
    @NotNull
    private int step;
    
    /** Specifies the position in the first axe*/
    @Column
    @NotNull
	private int x;
    
    /** Specifies the position in the second axe*/
    @Column
    @NotNull
	private int y;

    /** Specifies the position in the third axe*/
    @Column
    @NotNull
	private int z;
    
    /** The value to be stored in the x, y, z position*/
    @Column
    @NotNull
	private BigInteger value;
	
    /**
     * Need for JPA compliance
     */
	public Update() {
	}

	public Update(int x, int y, int z, BigInteger value) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.value = value;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public BigInteger getValue() {
		return value;
	}

	public void setValue(BigInteger value) {
		this.value = value;
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
