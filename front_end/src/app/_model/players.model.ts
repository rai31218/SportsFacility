import { Address } from "./address.model";

export class Players{
    public id?:string;
	public firstName?:string ;
	public lastName?:string;
	public dob?:string;
	public password?:string;
	public email?:string;
	public pan?:string;
	public active?:boolean;
	public age?:number;
	public address?:Address;
	public contact?:number;
	public registeredDate?:Date;


	constructor(id:string
		//,firstName:string,lastName:string,email:string,pan:string,age:number,contact:number
		){
			this.id=id;
			// this.firstName=firstName;
			// this.lastName=lastName;
			// this.email=email;
			// this.pan=pan;
			// this.age=this.age;
			// this.address=this.address
			// this.contact=contact;
		}
	

}