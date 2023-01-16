import { State } from "./state.model";

export class Country{
    public id:number;
    public name:string;
    public state:State[];

    constructor(id:number){
       
        this.id=id;
        
    }
}