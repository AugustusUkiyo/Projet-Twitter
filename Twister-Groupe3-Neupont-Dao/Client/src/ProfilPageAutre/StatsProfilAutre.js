import React, { Component } from 'react';
import axios from 'axios';
import './page_profil_autre.css';
import AddSupprFriend from './AddSupprFriend';

class StatsProfilAutre extends Component{
    constructor(props){
	super(props);
	this.state = {nom : "" , prenom : "" , nb_amis : 0 , nb_messages : 0}
	this.send = this.send.bind(this);
    }

    componentDidMount(){
	const formData = new URLSearchParams();
	formData.append("key",this.props.cle);
	formData.append("id",this.props.id_pro);
	axios.get('http://localhost:8080/TwisterGN&DH/Stats/StatsPerso?'+formData).then(reponse =>
											{
											    this.setState({nom : reponse.data["nom"] , prenom : reponse.data["prenom"], nb_amis : reponse.data["nb_amis"], nb_messages : reponse.data["nb_msgPerso"]})
											});
    }

    send(){
	this.props.profil(this.props.login_co)
    }
    
    render(){
	return (
		<div id="profil_pro_autre" >
		<span id="profil_user_pro_autre"> Profil </span><br></br>
		<div id="statsPersoAutre">
		<div id="nom_prenom_autre">
		<span>{this.state.prenom}</span><br></br><span>{this.state.nom}</span><br></br>		
	    </div>
		<div id="nb_msg_pro_autre">
		Nombre de messages : {this.state.nb_messages} 
	    </div>
		<div id="nb_amis_pro_autre">
		Nombre d amis : {this.state.nb_amis}  
	    </div>
		</div>
		<AddSupprFriend cle={this.props.cle} id_co={this.props.id_co} id_pro={this.props.id_pro}/>
	    </div>
	);
    }
}

export default StatsProfilAutre;
