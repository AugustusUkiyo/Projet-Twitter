import React, { Component } from 'react';
import NavigationPannel from './NavigationPannel';
import Signin from "./Signin";

class MainPage extends Component{
    constructor(props){
	super(props);
	this.state = {page_courante : "Login" , isConnected : false , cle : "", id_co : "",id_pro : "", text_search : ""};
	this.getConnected = this.getConnected.bind(this);
	this.setLogout = this.setLogout.bind(this);
	this.getSigned = this.getSigned.bind(this);
	this.getCreated = this.getCreated.bind(this);
	this.getProfil = this.getProfil.bind(this);
	this.getBackTwisterPage = this.getBackTwisterPage.bind(this);
	this.getSearch = this.getSearch.bind(this);
    }

    getConnected(keyp,id){
	this.setState({page_courante : "TwisterPage" , isConnected : true , cle : keyp, id_co : id, id_pro : "", text_search : ""});
    }

    setLogout(){
	this.setState({page_courante : "Login" , isConnected : false , cle : "", id_co : "", id_pro : "", text_search : ""});
    }

    getSigned(keyp){
	this.setState({page_courante : "Signin" , isConnected : false , cle : keyp, id_co : "", id_pro : "", text_search : ""});
    }

    getCreated(){
	this.setState({page_courante : "Login" , isConnected : false , cle : "", id_co : "", id_pro : "", text_search : ""});
    }

    getProfil(id_pro){
	this.setState({page_courante : "TwisterProfil" , isConnected : true , cle : this.state.cle, id_co : this.state.id_co, id_pro : id_pro, text_search : ""});
    }

    getBackTwisterPage(){
	this.setState({page_courante : "TwisterPage" , isConnected : true , cle : this.state.cle, id_co : this.state.id_co, id_pro : "", text_search : ""});
    }

    getSearch(text){
 	this.setState({page_courante : "SearchPage" , isConnected : true , cle : this.state.cle, id_co : this.state.id_co, id_pro : "", text_search : text});   	
    }
    
    render(){
	if((this.state.page_courante === "Signin")){
	    return ( <div className="MP">
		     <Signin login={this.getCreated} annuler={this.setLogout}/>
		     </div>);    
	}
	else{
	    return (<div className="MP">
		    <NavigationPannel pc={this.state.page_courante} cle={this.state.cle} id_co={this.state.id_co} id_pro={this.state.id_pro} sign={this.getSigned} login={this.getConnected} logout={this.setLogout} isConnected={this.state.isConnected} profil={this.getProfil} gbTwisterPage={this.getBackTwisterPage} search={this.getSearch} text_search={this.state.text_search}/>
		    </div>);	    
	}
    }

}

export default MainPage;
