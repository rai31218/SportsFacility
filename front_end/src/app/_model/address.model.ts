import { Country } from "./country.model";
import { State } from "./state.model";

export class Address{
    public id?:number;
	public homeAddress?:string;
	public state?:State;
	public country?:Country;

    constructor(homeAddress:string, state:State, country:Country){
       
        this.homeAddress=homeAddress;
        this.state=state;
        this.country=country;
    }
}