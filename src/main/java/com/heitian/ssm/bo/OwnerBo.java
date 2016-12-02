package com.heitian.ssm.bo;

import java.io.Serializable;
import com.heitian.ssm.model.Owner;

public class OwnerBo extends Owner implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public OwnerBo(){};
	 
    public OwnerBo(Owner owner){
        this.setId(owner.getId());
        this.setName(owner.getName());
        this.setPassword(owner.getPassword());
        this.setStatus(owner.getStatus());
        this.setEmail(owner.getEmail());
        this.setIsEmailVerified(owner.getIsEmailVerified());
    }

	@Override
	public String toString() {
		return "OwnerBo [getIsEmailVerified()=" + getIsEmailVerified()
				+ ", getEmail()=" + getEmail() + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getPassword()="
				+ getPassword() + ", getStatus()=" + getStatus()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	};
    
    

}
