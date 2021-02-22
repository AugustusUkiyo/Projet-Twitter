import React, { Component } from 'react';
import axios from 'axios';
import './page_principale.css';

class Stats extends Component{
    constructor(props){
	super(props);
	this.state = {nb_pers : 0 , nb_messages : 0}
	this.send = this.send.bind(this);
    }

    componentDidMount(){
	const formData = new URLSearchParams();
	formData.append("key",this.props.cle);
	axios.get('http://localhost:8080/TwisterGN&DH/Stats/Stats?'+formData).then(reponse =>
										   {
										       this.setState({nb_pers : reponse.data["nb_users"],nb_messages : reponse.data["nb_msg"]})
										   });
    }

    send(){
	this.props.profil(this.props.id_co)
    }
    
    render(){
	
	return (
		<div id="zone_stats">
		<div id="gotoprofil">
		<button id="buttongotoprofil" className="button" onClick={this.send}> Page Profil</button>
	        </div>
		<div id="nombre_personnes">
		Nombre de personnes inscrites : {this.state.nb_pers}
	        </div>
		<div id="nombre_messages">
		Nombre total de messages envoyés : {this.state.nb_messages}
	        </div>
		<p id="nom_site">Twister</p>
		</div>	    
	);
    }
}

export default Stats;

