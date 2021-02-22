import React, { Component } from 'react';
import axios from 'axios';
import './page_profil.css';

class StatsProfil extends Component{
    constructor(props){
	super(props);
	this.state = {nom : "" , prenom : "" , nb_amis : 0 , nb_messages : 0}
	this.send = this.send.bind(this);
    }

    componentDidMount(){
	const formData = new URLSearchParams();
	formData.append("key",this.props.cle);
	formData.append("id",this.props.id_co);
	axios.get('http://localhost:8080/TwisterGN&DH/Stats/StatsPerso?'+formData).then(reponse =>
											{
											    this.setState({nom : reponse.data["nom"] , prenom : reponse.data["prenom"], nb_amis : reponse.data["nb_amis"],nb_messages : reponse.data["nb_msgPerso"]});
											});
    }

    send(){
	this.props.profil(this.props.login_co)
    }
    
    render(){
	return (
		<div id="profil_pro" >
		<span id="profil_user_pro"> Profil </span><br></br>
		<div id="statsPerso">
		<div id="nom_prenom">
		<span>{this.state.prenom}</span><br></br><span>{this.state.nom}</span><br></br>
		</div>
		<div id="nb_msg_pro">
		Nombre de messages : {this.state.nb_messages} 
	    </div>
		<div id="nb_amis_pro">
		Nombre d amis : {this.state.nb_amis}  
	    </div>
		</div>
		</div>
	);
    }
}

export default StatsProfil;
